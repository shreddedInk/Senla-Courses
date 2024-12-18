package action;

public class MarkBookAsUnavailableAction implements IAction {
    private final ConsoleActions consoleActions;

    public MarkBookAsUnavailableAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.markBookAsUnavailable();
    }
}
