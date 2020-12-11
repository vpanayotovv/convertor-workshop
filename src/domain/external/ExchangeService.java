package domain.external;

import domain.entity.Money;

public interface ExchangeService {
    Money exchange(Money convertFrom, String toCurrency);
}
