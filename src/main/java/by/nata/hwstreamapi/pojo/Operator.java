package by.nata.hwstreamapi.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Operator {

    VODAFONE("+45"),
    ORANGE("+33"),
    TMOBILE("+49"),
    O2("+46"),
    THREE("+44");

    @Getter
    private final String operatorCode;

    @Override
    public String toString() {
        return this.name() + " (" + operatorCode + ")";
    }
}
