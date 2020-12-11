package exturnal;

import domain.entity.Money;
import domain.external.ExchangeService;

import java.math.BigDecimal;

public class StubbedExchangeService implements ExchangeService {
    @Override
    public Money exchange(Money convertFrom, String toCurrency) {
        return new Money(BigDecimal.ONE,"USD");
    }
}
