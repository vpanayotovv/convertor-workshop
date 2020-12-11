package domain.command;

import domain.io.Logger;
import domain.repository.ConversionHistoryRepository;

import java.util.List;

public class HistoryCommand implements Command<HistoryCommand.Input> {

    private final ConversionHistoryRepository repository;
    private final Logger logger;

    public HistoryCommand(ConversionHistoryRepository repository, Logger logger) {
        this.repository = repository;
        this.logger = logger;
    }

    @Override
    public void execute(Input input) {

        List<String> lastNConversions = repository.getLast(input.getCommandsToShow());

        lastNConversions.forEach(logger::logLine);
        //show last input
    }

    public static class Input extends EmptyInput{
        private final int commandsToShow;

        public Input(int commandsToShow) {

            if (commandsToShow< 1){
                throw new IllegalArgumentException("input must be positive integer");
            }

            this.commandsToShow = commandsToShow;
        }

        private int getCommandsToShow() {
            return this.commandsToShow;
        }
    }
}
