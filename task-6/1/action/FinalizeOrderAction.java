package action;

public class FinalizeOrderAction implements IAction {
    private final ConsoleActions consoleActions;

    public FinalizeOrderAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.finalizeOrder();
    }
}

