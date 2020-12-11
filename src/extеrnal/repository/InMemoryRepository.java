package extеrnal.repository;

import domain.entity.Money;
import domain.repository.ConversionHistoryRepository;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryRepository implements ConversionHistoryRepository {

    private final Deque<String> history;

    public InMemoryRepository() {
        this.history = new ArrayDeque<>();
    }

    @Override
    public List<String> getLast(int n) {
        return history.stream().limit(n).collect(Collectors.toList());
    }

    @Override
    public void save(Money from, Money to) {

        String format = String.format("from: %s to %s", from, to);
        history.push(format);
    }
}
