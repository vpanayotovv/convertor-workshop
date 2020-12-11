package exturnal.repository;

import domain.repository.ConversionHistoryRepository;

import java.util.List;

public class InMemoryRepository implements ConversionHistoryRepository {
    @Override
    public List<String> getLast(int n) {
        return null;
    }
}
