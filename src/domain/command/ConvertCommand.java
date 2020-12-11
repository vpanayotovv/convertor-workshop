package domain.command;

import domain.entity.Money;
import domain.external.ExchangeService;
import domain.io.Logger;

public class ConvertCommand implements Command<ConvertCommand.Input> {

    private final ExchangeService exchangeService;
    private final Logger logger;

    public ConvertCommand(ExchangeService exchangeService, Logger logger){
        this.exchangeService = exchangeService;
        this.logger = logger;
    }

    @Override
    public void execute(Input input) {
       Money converted = exchangeService.exchange(input.convertFrom,input.toCurrency);
       logger.logLine(converted.toString());
    }

    public static class Input extends EmptyInput{
        private final Money convertFrom;
        private final String toCurrency;

        public Input(Money convertFrom, String toCurrency) {
            this.convertFrom = convertFrom;
            this.toCurrency = toCurrency;
        }

        public Money getConvertFrom() {
            return this.convertFrom;
        }

        public String getToCurrency() {
            return this.toCurrency;
        }
    }
}
