package console;

import domain.io.Logger;

public class ConsoleLogger implements Logger {
    @Override
    public void logLine(String line) {
        System.out.println(line);
    }
}
