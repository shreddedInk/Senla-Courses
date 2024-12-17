package view;

import action.*;
import action.exporting.*;
import action.getinfo.GetCompletedOrderCountByPeriodAction;
import action.getinfo.GetCompletedOrdersByPeriodAction;
import action.getinfo.GetTotalEarningsByPeriodAction;
import action.importing.*;
import action.sorting.*;
import model.bookstore.BookStore;

import view.menu.*;

import java.util.ArrayList;
import java.util.List;

public class Builder {
    private Menu rootMenu;

    public Menu buildMenu(BookStore bookStore, ConsoleActions consoleActions) {
        List<MenuItem> mainMenuItems = new ArrayList<>();
        mainMenuItems.add(new MenuItem("List Books", new ViewBooksAction(consoleActions), buildBookSortMenu(consoleActions)));
        mainMenuItems.add(new MenuItem("List Orders", new ViewOrdersAction(consoleActions), buildOrderSortMenu(consoleActions)));
        mainMenuItems.add(new MenuItem("Create Order", new CreateOrderAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Finalize Order", new FinalizeOrderAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Resolve Book Request", new ResolveBookRequestAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("List Stale Books", new ViewStaleBooksAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Import Books", new ImportBooksAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Export Books", new ExportBooksAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Import Orders", new ImportOrdersAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Export Orders", new ExportOrdersAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Import Requests", new ImportRequestsAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Export Requests", new ExportRequestsAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Get Completed Orders by Period", new GetCompletedOrdersByPeriodAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Get Total Earnings by Period", new GetTotalEarningsByPeriodAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Get Completed Order Count by Period", new GetCompletedOrderCountByPeriodAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Mark Book as Unavailable", new MarkBookAsUnavailableAction(consoleActions), null));
        mainMenuItems.add(new MenuItem("Exit", null, null));
        rootMenu = new Menu("Main Menu", mainMenuItems);
        return rootMenu;
    }

    private Menu buildBookSortMenu(ConsoleActions consoleActions) {
        List<MenuItem> bookSortMenuItems = new ArrayList<>();
        bookSortMenuItems.add(new MenuItem("Sort by Name", new ViewBooksByNameAction(consoleActions), null));
        bookSortMenuItems.add(new MenuItem("Sort by Publishing Year", new ViewBooksByPublishingYearAction(consoleActions), null));
        bookSortMenuItems.add(new MenuItem("Sort by Price", new ViewBooksByPriceAction(consoleActions), null));
        bookSortMenuItems.add(new MenuItem("Sort by Availability", new ViewBooksByAvailabilityAction(consoleActions), null));
        bookSortMenuItems.add(new MenuItem("Back to Main Menu", null, null));
        return new Menu("Book Sort Menu", bookSortMenuItems);
    }

    private Menu buildOrderSortMenu(ConsoleActions consoleActions) {
        List<MenuItem> orderSortMenuItems = new ArrayList<>();
        orderSortMenuItems.add(new MenuItem("Sort by Order Date", new ViewOrdersByDateAction(consoleActions), null));
        orderSortMenuItems.add(new MenuItem("Sort by Total Price", new ViewOrdersByTotalPriceAction(consoleActions), null));
        orderSortMenuItems.add(new MenuItem("Sort by Status", new ViewOrdersByStatusAction(consoleActions), null));
        orderSortMenuItems.add(new MenuItem("Back to Main Menu", null, null));
        return new Menu("Order Sort Menu", orderSortMenuItems);
    }

    public Menu getRootMenu() {
        return rootMenu;
    }

}
