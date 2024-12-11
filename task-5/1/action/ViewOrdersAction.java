package action;

public class ViewOrdersAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewOrdersAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewOrders();
    }
}