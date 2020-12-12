package console;

import domain.external.ExchangeService;
import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;
import extеrnal.CurrConvExchangeService;
import extеrnal.repository.InMemoryRepository;
import java.util.Scanner;

public class ConsoleRunner {


    public void run() {
        Scanner scanner = new Scanner(System.in);

        ExchangeService exchangeService = new CurrConvExchangeService();
        Logger logger = new ConsoleLogger();
        ConversionHistoryRepository conversionHistoryRepository = new InMemoryRepository();

        ConsoleCommandParser parser =
                new ConsoleCommandParser(conversionHistoryRepository,logger,exchangeService);

        while (true) {
            String line = scanner.nextLine();
            String[] split = line.split("\\s+");
            parser.execute(split);
        }
    }
}
