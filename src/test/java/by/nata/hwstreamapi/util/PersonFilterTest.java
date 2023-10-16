package by.nata.hwstreamapi.util;

import by.nata.hwstreamapi.pojo.Gender;
import by.nata.hwstreamapi.pojo.Operator;
import by.nata.hwstreamapi.pojo.Person;
import by.nata.hwstreamapi.pojo.Phone;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

class PersonFilterTest {

    private List<Person> testPersons;

    @BeforeEach
    void setUp() {
        testPersons = PersonTestUtil.createTestPersons();
    }

    //Получите список Person и отфильтруйте только те, у которых age > n и выведите в консоль.
    @ParameterizedTest
    @ValueSource(ints = {18, 31, 40})
    void testAgeFilterAndPrint(int ageFilter) {
        System.out.println("Persons older than " + ageFilter + " years:");
        testPersons.stream()
                .filter(person -> person.getAge() > ageFilter)
                .forEach(System.out::println);
    }

    //Получите список Person, отфильтруйте только те, у кого weight > n, преобразуйте в name и выведите в консоль.
    @ParameterizedTest
    @ValueSource(doubles = {70.0, 80.0, 90.0})
    void testWeightFilterAndPrint(double weightFilter) {
        System.out.println("Persons with weight greater than " + weightFilter + " kg:");
        testPersons.stream()
                .filter(person -> person.getWeight() > weightFilter)
                .map(Person::getName)
                .forEach(System.out::println);
    }

    //Получите список Person, отфильтруйте только те, у кого кол-во телефонов > n, преобразуйте в номера телефонов и выведите в консоль.
    @Test
    void testPhoneCountFilterAndPrint() {
        System.out.println("Persons with more than 1 phone:");
        testPersons.stream()
                .filter(person -> person.getPhones().size() > 1)
                .flatMap(person -> person.getPhones().stream())
                .map(Phone::getNumber)
                .forEach(System.out::println);
    }

    //Получите список Person, преобразуйте в name и затем преобразуйте в строку, что бы имена были через запятую.
    @Test
    void testNameListToStringAndPrint() {
        String names = testPersons.stream()
                .map(Person::getName) // Преобразование в имена
                .collect(Collectors.joining(", "));

        System.out.println("Names separated by comma: " + names);
    }

    //Получите список Person и отсортируйте их по возрасту в порядке убывания, если возраст равен, то по именам и выведите в консоль.
    @Test
    void testSortByAgeAndName() {
        testPersons.stream()
                .sorted((p1, p2) -> {
                    if (p1.getAge() == p2.getAge()) {
                        return p1.getName().compareTo(p2.getName());
                    } else {
                        return Integer.compare(p2.getAge(), p1.getAge());
                    }
                })
                .forEach(System.out::println);
    }

    //Получите список Person и сгруппируйте их по полу.
    @Test
    void testGroupByGender() {
        List<Person> testPersons = PersonTestUtil.createTestPersons();

        Map<Gender, List<Person>> groupedByGender = testPersons.stream()
                .collect(Collectors.groupingBy(Person::getGender));

        groupedByGender.forEach((gender, persons) -> {
            System.out.println("Persons with gender " + gender + ":");
            persons.forEach(System.out::println);
            System.out.println();
        });
    }

    //Получите список Person и проверьте есть ли в этом списке человек, у которого номер телефона N значению.
    @Test
    void testContainsPhoneNumber() {
        String phoneNumberToCheck = "152347525";

        boolean personWithPhoneNumberExists = testPersons.stream()
                .anyMatch(person -> person.getPhones()
                        .stream()
                        .anyMatch(phone -> phone.getNumber().equals(phoneNumberToCheck))
                );

        if (personWithPhoneNumberExists) {
            System.out.println("Person with phone number " + phoneNumberToCheck + " exists.");
        } else {
            System.out.println("Person with phone number " + phoneNumberToCheck + " does not exist.");
        }
    }

    //Получите список Person, получите n по порядку человека и получите операторов его телефонов исключая дублирование.
    @ParameterizedTest
    @ValueSource(ints = {1, 5, 10})
    void testGetUniqueOperatorsOfNthPerson(int personNumber) {
        personNumber--;

        if (personNumber >= 0 && personNumber < testPersons.size()) {
            List<Operator> uniqueOperators = testPersons.get(personNumber)
                    .getPhones()
                    .stream()
                    .map(Phone::getOperator)
                    .distinct()
                    .toList();

            System.out.println("Unique operators of the " + (personNumber + 1) + "th person: " + uniqueOperators);
        } else {
            System.out.println("Invalid index. Person not found.");
        }
    }

    //Получите список Person и получите их средний вес.
    @Test
    void testGetAverageWeight() {
        double averageWeight = testPersons.stream()
                .collect(Collectors.averagingDouble(Person::getWeight));

        System.out.println("Average weight of persons: " + averageWeight + " kg");
    }

    //Получите список Person и найдите самого младшего по возрасту.
    @Test
    void testFindYoungestPerson() {
        Optional<Person> youngestPerson = testPersons.stream()
                .min(Comparator.comparingInt(Person::getAge));

        if (youngestPerson.isPresent()) {
            System.out.println("Youngest person: " + youngestPerson.get());
        } else {
            System.out.println("No persons in the list.");
        }
    }

    //Получите список Person, получите их телефоны, сгруппируйте по оператору и результатом группировки должны быть только номера телефонов.
    @Test
    void testGroupPhonesByOperator() {
        Map<Operator, List<String>> phonesByOperator = testPersons.stream()
                .flatMap(person -> person.getPhones().stream())
                .collect(Collectors.groupingBy(
                        Phone::getOperator,
                        Collectors.mapping(Phone::getNumber, Collectors.toList())
                ));

        phonesByOperator.forEach((operator, phoneNumbers) -> {
            System.out.println("Operator: " + operator);
            System.out.println("Phone numbers: " + phoneNumbers);
            System.out.println();
        });
    }

    //Получите список Person, сгруппируйте их по полу и результатом группировки должно быть их кол-во.
    @Test
    void testGroupByGenderAndCount() {
        Map<Gender, Long> countByGender = testPersons.stream()
                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));

        countByGender.forEach((gender, count) -> {
            System.out.println("Gender: " + gender);
            System.out.println("Count: " + count);
        });
    }

}
