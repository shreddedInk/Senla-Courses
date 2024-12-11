package controller;

import model.BookStore;

public class ViewBooksByNameAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewBooksByNameAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewBooksByName();
    }
}