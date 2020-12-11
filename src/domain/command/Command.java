package domain.command;

public interface Command<T extends EmptyInput> {

    void execute(T input);
}
