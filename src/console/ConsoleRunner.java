package console;

import domain.command.ConvertCommand;
import domain.command.EmptyInput;
import domain.command.EndCommand;
import domain.entity.Money;
import domain.external.ExchangeService;
import domain.io.Logger;
import exturnal.StubbedExchangeService;

import java.math.BigDecimal;
import java.util.Scanner;

public class ConsoleRunner {

    private static ExchangeService exchangeService = new StubbedExchangeService();
    private static Logger logger = new ConsoleLogger();

    public void run(){
        Scanner scanner = new Scanner(System.in);
        while (true){
            String line = scanner.nextLine();
            String[] split = line.split("\\s+");

            switch (split[0]){
                case"END":
                    end();
                    break;

                case "CONVERT":
                    convert(split);
                    break;
                default:
                    logger.logLine("incorrect command");
                    break;
            }

        }
    }

    private void convert(String[] split) {

        BigDecimal fromValue = new BigDecimal(split[1]);
        String fromCurrency = split[2];
        String toCurrency = split[3];

        ConvertCommand.Input input = new ConvertCommand.Input(
                new Money(fromValue,fromCurrency),toCurrency
        );

        new ConvertCommand(exchangeService,logger).execute(input);
    }

    private void end() {
        new EndCommand().execute(new EmptyInput());
    }
}
