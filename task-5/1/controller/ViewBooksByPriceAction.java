package controller;

import model.BookStore;

public class ViewBooksByPriceAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewBooksByPriceAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewBooksByPrice();
    }
}