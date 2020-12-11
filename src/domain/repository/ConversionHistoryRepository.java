package domain.repository;

import domain.entity.Money;

import java.util.List;

public interface ConversionHistoryRepository {
    List<String> getLast(int n);

    void save(Money from, Money to);
}
