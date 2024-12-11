package controller;

import model.BookStore;

public class ViewOrdersByTotalPriceAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewOrdersByTotalPriceAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewOrdersByTotalPrice();
    }
}