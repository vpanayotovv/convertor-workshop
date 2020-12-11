package domain.repository;

import java.util.List;

public interface ConversionHistoryRepository {
    List<String> getLast(int n);
}
