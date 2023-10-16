package by.nata.hwstreamapi.pojo;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@EqualsAndHashCode
public class Phone {

    private Operator operator;
    private String number;

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Phone{");
        sb.append("operator=").append(operator);
        sb.append(", number='").append(number).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
