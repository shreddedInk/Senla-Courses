package action;

public class CreateOrderAction implements IAction {
    private final ConsoleActions consoleActions;

    public CreateOrderAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.createOrder();
    }
}