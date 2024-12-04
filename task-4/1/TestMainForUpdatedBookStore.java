import java.text.SimpleDateFormat;
import java.util.*;

public class TestMainForUpdatedBookStore {
    public static void main(String[] args) throws Exception {
        // Создание книг
        Book book1 = new Book("Mathematics", "Author A", 1997, 50.0, true);
        Book book2 = new Book("English for Mathematics", "Author B", 1998, 30.0, true);
        Book book3 = new Book("Java Basics", "Author C", 1999, 40.0, false);
        Book book4 = new Book("Data Structures", "Author D", 2000, 60.0, false);

        Set<Book> bookSet = new HashSet<>(Arrays.asList(book1, book2, book3, book4));
        LibraryInventory libraryInventory = new LibraryInventory();

        // Создание BookStore
        BookStore bookStore = new BookStore(bookSet, libraryInventory);



        // ======== ТЕСТ СОРТИРОВОК ========
        System.out.println("=== ТЕСТ СОРТИРОВОК ===");



        // ======== СПИСОК КНИГ ========
        System.out.println("\n=== СПИСОК КНИГ ===");

// Сортировка по алфавиту (название книги)
        System.out.println("\nСортировка книг по алфавиту:");
        bookStore.sortBooks(Comparator.comparing(Book::getName))
                .forEach(book -> System.out.println(book.getDescription()));

// Сортировка по дате издания
        System.out.println("\nСортировка книг по дате издания:");
        bookStore.sortBooks(Comparator.comparing(Book::getPublishingYear))
                .forEach(book -> System.out.println(book.getDescription()));

// Сортировка по цене
        System.out.println("\nСортировка книг по цене:");
        bookStore.sortBooks(Comparator.comparing(Book::getPrice))
                .forEach(book -> System.out.println(book.getDescription()));

// Сортировка по наличию на складе
        System.out.println("\nСортировка книг по наличию на складе:");
        bookStore.sortBooks(Comparator.comparing(Book::isAvailable).reversed())
                .forEach(book -> System.out.println(book.getDescription()));



        // ======== СПИСОК ЗАКАЗОВ ========
        System.out.println("\n=== ТЕСТЫ: СПИСОК ЗАКАЗОВ ===");

// Сортировка по дате исполнения
        System.out.println("\nСортировка заказов по дате исполнения:");
        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getOrderDate))
                .forEach(order -> System.out.println(order.getOrderDetails()));

// Сортировка по суммарной цене книг в заказе
        System.out.println("\nСортировка заказов по цене:");
        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getTotalPrice).reversed())
                .forEach(order -> System.out.println(order.getOrderDetails()));

// Сортировка по статусу заказа
        System.out.println("\nСортировка заказов по статусу:");
        bookStore.sortOrders(Comparator.comparing(PurchaseOrder::getStatus))
                .forEach(order -> System.out.println(order.getOrderDetails()));

        // ======== СПИСОК ЗАПРОСОВ НА КНИГИ ========
        System.out.println("\n=== СПИСОК ЗАПРОСОВ НА КНИГИ ===");

// Сортировка по количеству запросов
        System.out.println("\nСортировка запросов по количеству:");
        bookStore.sortRequests(Comparator.comparing(
                        book -> bookStore.getPendingRequests().stream()
                                .filter(request -> request.getRequestedBook().equals(book))
                                .count(),
                        Comparator.reverseOrder()))
                .forEach(book -> System.out.println(book.getName()));

// Сортировка по количеству запросов
        System.out.println("\nСортировка запросов по алфавиту:");
        bookStore.sortRequests(Comparator.comparing(Book::getName))
                .forEach(book -> System.out.println(book.getName()));



        // ======== ТЕСТ ЗАКАЗОВ И ОПЕРАЦИЙ С НИМИ ========
        System.out.println("\n=== ТЕСТ ЗАКАЗОВ И ОПЕРАЦИЙ ===");

        // Создание заказа
        Set<Book> orderSet1 = new HashSet<>(Arrays.asList(book1, book2));
        PurchaseOrder order1 = bookStore.generateOrder(orderSet1);

        // Создание другого заказа с недоступными книгами
        Set<Book> orderSet2 = new HashSet<>(Arrays.asList(book3, book4));
        PurchaseOrder order2 = bookStore.generateOrder(orderSet2);

        // Закрытие заказа 1
        bookStore.finalizeOrder(order1);

        // Попытка закрыть заказ 2 (должно не сработать)
        bookStore.finalizeOrder(order2);

        // Обработка запросов (делаем книги доступными)
        bookStore.resolveBookRequest(book3);
        bookStore.resolveBookRequest(book4);

        // Финализируем заказ 2
        bookStore.finalizeOrder(order2);



        // ======== ТЕСТ ПЕРИОДА И ВЫПОЛНЕННЫХ ЗАКАЗОВ ========
        System.out.println("\n=== ТЕСТ ПЕРИОДА И ВЫПОЛНЕННЫХ ЗАКАЗОВ ===");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = sdf.parse("2023-01-01");
        Date endDate = sdf.parse("2023-12-31");

        // Заказы за период
        System.out.println("\nВыполненные заказы между " + sdf.format(startDate) + " и " + sdf.format(endDate) + ":");
        bookStore.getCompletedOrdersByPeriod(startDate, endDate, Comparator.comparing(PurchaseOrder::getOrderDate))
                .forEach(order -> System.out.println(order.getOrderDetails()));

        // Подсчёт заработка за период
        double totalEarnings = bookStore.getTotalEarningsByPeriod(startDate, endDate);
        System.out.println("\nОбщая выручка за период: $" + totalEarnings);

        // Подсчёт заказов за период
        long completedOrderCount = bookStore.getCompletedOrderCountByPeriod(startDate, endDate);
        System.out.println("\nКоличество выполненных заказов за период: " + completedOrderCount);

        // Сортировка выполненных заказов по дате
        System.out.println("\nСортировка выполненных заказов по дате:");
        bookStore.getCompletedOrdersByPeriod(startDate, endDate, Comparator.comparing(PurchaseOrder::getOrderDate))
                .forEach(order -> System.out.println(order.getOrderDetails()));

// Сортировка выполненных заказов по общей цене
        System.out.println("\nСортировка выполненных заказов по общей цене:");
        bookStore.getCompletedOrdersByPeriod(startDate, endDate, Comparator.comparing(PurchaseOrder::getTotalPrice).reversed())
                .forEach(order -> System.out.println(order.getOrderDetails()));



        // ======== ТЕСТ ЗАЛЕЖАВШИХСЯ КНИГ ========
        System.out.println("\n=== ТЕСТ ЗАЛЕЖАВШИХСЯ КНИГ ===");
        Date currentDate = new Date();
        bookStore.getStaleBooks(new Date()).forEach(book -> System.out.println(book.getDescription()));

        // Сортировка залежавшихся книг по дате поступления
        System.out.println("\nСортировка залежавшихся книг по дате поступления:");
        bookStore.getStaleBooks(currentDate).stream()
                .sorted(Comparator.comparing(Book::getLastSoldDate, Comparator.nullsFirst(Comparator.naturalOrder())))
                .forEach(book -> System.out.println(book.getDescription()));

// Сортировка залежавшихся книг по цене
        System.out.println("\nСортировка залежавшихся книг по цене:");
        bookStore.getStaleBooks(currentDate).stream()
                .sorted(Comparator.comparing(Book::getPrice))
                .forEach(book -> System.out.println(book.getDescription()));



        // ======== ТЕСТ ЗАПРОСОВ НА КНИГИ ========
        System.out.println("\n=== ТЕСТ ЗАПРОСОВ ===");

        System.out.println("\nСписок запросов:");
        bookStore.getPendingRequests()
                .forEach(request -> System.out.println(request.getRequestedBook().getName()));



        // ======== ПРОВЕРКА ОТДЕЛЬНЫХ ОПЕРАЦИЙ ========
        System.out.println("\n=== ПРОВЕРКА ОТДЕЛЬНЫХ ОПЕРАЦИЙ ===");

        System.out.println("\nДетали заказа 1:");
        System.out.println(order1.getOrderDetails());

        System.out.println("\nОписание книги 1:");
        System.out.println(book1.getDescription());
    }
}
