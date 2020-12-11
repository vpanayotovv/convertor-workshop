package domain.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Money {

    private final BigDecimal value;
    private final String currency;


    public Money(BigDecimal value, String currency) {
        this.value = value;
        this.currency = currency;
        this.validate();
    }

    private void validate(){
        List<String> invalidFields = new ArrayList<>();

        if (this.value == null || this.value.compareTo(BigDecimal.ZERO) <= 0){
            invalidFields.add("value must not be null or negative");
        }
        if (this.currency == null || this.currency.length() != 3){
            invalidFields.add("currency must not string of size 3 and non-null");
        }

        if (!invalidFields.isEmpty()){
            throw new IllegalArgumentException("Invalid fields " + invalidFields);
        }

    }

    public BigDecimal getValue() {
        return this.value;
    }

    public String getCurrency() {
        return this.currency;
    }

    @Override
    public String toString() {
        return "Money{" +
                "value=" + value +
                ", currency='" + currency + '\'' +
                '}';
    }
}
