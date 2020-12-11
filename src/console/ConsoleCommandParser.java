package console;

import domain.command.*;
import domain.entity.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

import java.math.BigDecimal;

public class ConsoleCommandParser {

    private ConversionHistoryRepository conversionHistoryRepository;
    private Logger logger;
    private ExchangeService exchangeService;

    public ConsoleCommandParser(ConversionHistoryRepository conversionHistoryRepository, Logger logger, ExchangeService exchangeService) {
        this.conversionHistoryRepository = conversionHistoryRepository;
        this.logger = logger;
        this.exchangeService = exchangeService;
    }


    void execute(String[] args) {
        switch (args[0]) {
            case "END":
                end();
                break;

            case "CONVERT":
                convert(args);
                break;

            case "HISTORY":
                history(args);
                break;

            default:
                logger.logLine("incorrect command");
                break;
        }
    }

    private void end() {
        new EndCommand().execute(new EmptyInput());
    }

    private void convert(String[] split) {

        BigDecimal fromValue = new BigDecimal(split[1]);
        String fromCurrency = split[2];
        String toCurrency = split[3];

        ConvertCommand.Input input = new ConvertCommand.Input(
                new Money(fromValue, fromCurrency), toCurrency
        );

        new HistorySavingConvertCommand(exchangeService, logger, conversionHistoryRepository).execute(input);
    }

    private void history(String[] split) {
        Command<HistoryCommand.Input> cmd = new HistoryCommand(conversionHistoryRepository, logger);
        cmd.execute(new HistoryCommand.Input(Integer.parseInt(split[1])));
    }

}
