package action;

public class ResolveBookRequestAction implements IAction {
    private final ConsoleActions consoleActions;

    public ResolveBookRequestAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.resolveBookRequest();
    }
}