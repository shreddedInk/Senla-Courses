package controller;

public class ViewStaleBooksAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewStaleBooksAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewStaleBooks();
    }
}