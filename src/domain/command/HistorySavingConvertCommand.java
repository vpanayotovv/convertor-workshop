package domain.command;

import domain.entity.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

public class HistorySavingConvertCommand extends ConvertCommand {
    private final ConversionHistoryRepository repository;

    public HistorySavingConvertCommand(ExchangeService exchangeService, Logger logger, ConversionHistoryRepository repository) {
        super(exchangeService, logger);
        this.repository = repository;
    }

    @Override
    public void execute(Input input) {
        Money converted = protectedExecute(input);
        repository.save(input.getConvertFrom(),converted);
    }
}
