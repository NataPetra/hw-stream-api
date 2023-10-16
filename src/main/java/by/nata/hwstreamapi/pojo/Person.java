package by.nata.hwstreamapi.pojo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Person {

    private String name;
    private int age;
    private double weight;
    private Gender gender;
    private List<Phone> phones;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Person{");
        sb.append("name='").append(name).append('\'');
        sb.append(", age=").append(age);
        sb.append(", weight=").append(weight);
        sb.append(", gender=").append(gender);
        sb.append(", phones=").append(phones.toString());
        sb.append('}');
        return sb.toString();
    }
}
