package controller;

import model.BookStore;

public class ViewBooksByPublishingYearAction implements IAction {
    private final ConsoleActions consoleActions;

    public ViewBooksByPublishingYearAction(ConsoleActions consoleActions) {
        this.consoleActions = consoleActions;
    }

    @Override
    public void execute() {
        consoleActions.viewBooksByPublishingYear();
    }
}