package domain.command;

public class EndCommand implements Command {
    @Override
    public void execute(EmptyInput emptyInput) {
        System.exit(0);
    }
}
