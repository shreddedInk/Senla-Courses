package action;

public class ViewOrdersByStatusAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewOrdersByStatusAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewOrdersByStatus();
    }
}