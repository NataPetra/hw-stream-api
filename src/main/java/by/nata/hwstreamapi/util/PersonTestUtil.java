package by.nata.hwstreamapi.util;

import by.nata.hwstreamapi.pojo.Gender;
import by.nata.hwstreamapi.pojo.Operator;
import by.nata.hwstreamapi.pojo.Person;
import by.nata.hwstreamapi.pojo.Phone;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class PersonTestUtil {

    public static List<Person> createTestPersons() {

        List<Person> persons = new ArrayList<>();
        persons.add(Person.builder()
                .name("Ivan")
                .age(30)
                .weight(75.0)
                .gender(Gender.MALE)
                .phones(createTestPhones(Boolean.FALSE))
                .build());
        persons.add(Person.builder()
                .name("Ekaterina")
                .age(25)
                .weight(60.5)
                .gender(Gender.FEMALE)
                .phones(createTestPhones(Boolean.FALSE))
                .build());
        persons.add(Person.builder()
                .name("Alexey")
                .age(35)
                .weight(80.2)
                .gender(Gender.MALE)
                .phones(createTestPhones(Boolean.TRUE))
                .build());
        persons.add(Person.builder()
                .name("Olga")
                .age(28)
                .weight(55.5)
                .gender(Gender.FEMALE)
                .phones(createTestPhones(Boolean.TRUE))
                .build());
        persons.add(Person.builder()
                .name("Dmitry")
                .age(41)
                .weight(90.0)
                .gender(Gender.MALE)
                .phones(createTestPhones(Boolean.TRUE))
                .build());
        persons.add(Person.builder()
                .name("Natalia")
                .age(32)
                .weight(70.0)
                .gender(Gender.FEMALE)
                .phones(createTestPhones(Boolean.FALSE))
                .build());
        persons.add(Person.builder()
                .name("Andrey")
                .age(26)
                .weight(78.5)
                .gender(Gender.MALE)
                .phones(createTestPhones(Boolean.FALSE))
                .build());
        persons.add(Person.builder()
                .name("Svetlana")
                .age(29)
                .weight(63.2)
                .gender(Gender.FEMALE)
                .phones(createTestPhones(Boolean.FALSE))
                .build());
        persons.add(Person.builder()
                .name("Sergey")
                .age(32)
                .weight(85.0)
                .gender(Gender.MALE)
                .phones(createTestPhones(Boolean.FALSE))
                .build());
        persons.add(Person.builder()
                .name("Maria")
                .age(27)
                .weight(68.7)
                .gender(Gender.FEMALE)
                .phones(createTestPhones(Boolean.FALSE))
                .build());

        return persons;
    }

    private static List<Phone> createTestPhones(boolean twoPhones) {
        List<Phone> phones = new ArrayList<>();
        phones.add(Phone.builder()
                .operator(getRandomOperator())
                .number(generateRandomPhoneNumber())
                .build());
        if (twoPhones) {
            phones.add(Phone.builder()
                    .operator(getRandomOperator())
                    .number(generateRandomPhoneNumber())
                    .build());
        }
        return phones;
    }

    private static String generateRandomPhoneNumber() {
        return String.valueOf(100_000_000 + new Random().nextInt(900_000_000));
    }

    private static Operator getRandomOperator() {
        Operator[] operators = Operator.values();
        int randomIndex = new Random().nextInt(operators.length);
        return operators[randomIndex];
    }
}
