package action;

public class ViewBooksAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewBooksAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewBooks();
    }
}