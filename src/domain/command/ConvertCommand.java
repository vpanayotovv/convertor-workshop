package domain.command;

import domain.entity.Money;

public class ConvertCommand implements Command<ConvertCommand.Input> {
    @Override
    public void execute(Input input) {

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
