/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;
import entity.Client;
import entity.Order;
import entity.User;
import java.util.Comparator;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;
import tools.InputProtection;


public class OrderManager {

    private final Scanner scanner;
    private final BookManager bookManager;
    //private final ClientManager clientManager;
    private final DatabaseManager databaseManager;
    private final UserManager userManager;

    public OrderManager(Scanner scanner, UserManager userManager, BookManager bookManager, DatabaseManager databaseManager) {
        this.scanner = scanner;
        //this.clientManager = clientManager;
        this.userManager = userManager;
        this.bookManager = bookManager;
        this.databaseManager = databaseManager;
    }
        public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public Order makeOrder() {
        Order order = new Order();
        bookManager.printListBooks();
        System.out.print("Enter the number of desired book: ");
        int bookNumber = InputProtection.intInput(1, null);
        //Book selectedBook = books.get(bookNumber - 1);
        Book selectedBook = getDatabaseManager().getBook((long)bookNumber);//Ставим book под управление em

        //clientManager.printListClients();
        userManager.printListUsers(getDatabaseManager().getListUsers());
        System.out.print("Enter the number of the client: ");
        List <User> users = getDatabaseManager().getListUsers();
        int userNumber = InputProtection.intInput(1, users.size());
        User selectedUser = users.get(userNumber - 1);
        Client selectedClient = selectedUser.getClient();

        order.setBook(selectedBook);
        order.setUser(selectedUser);
        order.setClient(selectedClient);

         System.out.print("Enter the number of units sold: ");
        int unitsSold = InputProtection.intInput(1, Integer.MAX_VALUE);
        order.setUnitsSold(unitsSold);

        order.setOrderDate(new GregorianCalendar().getTime());

        selectedBook.setQuantity(selectedBook.getQuantity() - unitsSold);
        selectedUser.getClient().setBalance(selectedUser.getClient().getBalance() - selectedBook.getPrice()*unitsSold);

        return order;
    }

    
    public void printListOrdersFiltered() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select filter option:");
        System.out.println("1. All time");
        System.out.println("2. Specific year");
        System.out.println("3. Specific month of a year");
        System.out.println("4. Specific day of a month of a year");
        System.out.print("Enter your choice: ");
        
        List <Order> orders = getDatabaseManager().getListOrders();
        
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                orders = getDatabaseManager().getListOrders();
                //printListOrders(orders);
                
                break;
            case 2:
                System.out.print("Enter the year: ");
                int year = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByYear(year));
                orders = getDatabaseManager().getListOrdersByYear(year);
                
                break;
            case 3:
                System.out.print("Enter the year: ");
                int yearMonth = scanner.nextInt();
                System.out.print("Enter the month (1-12): ");
                int month = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByYearAndMonth(yearMonth, month));
                orders = getDatabaseManager().getListOrdersByYearAndMonth(yearMonth, month);
                break;
            case 4:
                System.out.print("Enter the year: ");
                int yearDay = scanner.nextInt();
                System.out.print("Enter the month (1-12): ");
                int monthDay = scanner.nextInt();
                System.out.print("Enter the day: ");
                int day = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByDate(yearDay, monthDay, day));
                orders = getDatabaseManager().getListOrdersByDate(yearDay, monthDay, day);
                break;
            default:
                System.out.println("Invalid choice.");
        }
        printListOrders(orders);
    }
    
    
    
    
    
    
    public void printListClientsFiltered() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select filter option:");
        System.out.println("1. All time");
        System.out.println("2. Specific year");
        System.out.println("3. Specific month of a year");
        System.out.println("4. Specific day of a month of a year");
        System.out.print("Enter your choice: ");
        
        List <Order> orders = getDatabaseManager().getListOrders();
        
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                orders = getDatabaseManager().getListOrders();
                //printListOrders(orders);
                
                break;
            case 2:
                System.out.print("Enter the year: ");
                int year = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByYear(year));
                orders = getDatabaseManager().getListOrdersByYear(year);
                
                break;
            case 3:
                System.out.print("Enter the year: ");
                int yearMonth = scanner.nextInt();
                System.out.print("Enter the month (1-12): ");
                int month = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByYearAndMonth(yearMonth, month));
                orders = getDatabaseManager().getListOrdersByYearAndMonth(yearMonth, month);
                break;
            case 4:
                System.out.print("Enter the year: ");
                int yearDay = scanner.nextInt();
                System.out.print("Enter the month (1-12): ");
                int monthDay = scanner.nextInt();
                System.out.print("Enter the day: ");
                int day = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByDate(yearDay, monthDay, day));
                orders = getDatabaseManager().getListOrdersByDate(yearDay, monthDay, day);
                break;
            default:
                System.out.println("Invalid choice.");
        }
        clientRating(orders);
    }
    
    
    
    
    public void printListBooksFiltered() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Select filter option:");
        System.out.println("1. All time");
        System.out.println("2. Specific year");
        System.out.println("3. Specific month of a year");
        System.out.println("4. Specific day of a month of a year");
        System.out.print("Enter your choice: ");
        
        List <Order> orders = getDatabaseManager().getListOrders();
        
        int choice = scanner.nextInt();
        
        switch (choice) {
            case 1:
                orders = getDatabaseManager().getListOrders();
                //printListOrders(orders);
                
                break;
            case 2:
                System.out.print("Enter the year: ");
                int year = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByYear(year));
                orders = getDatabaseManager().getListOrdersByYear(year);
                
                break;
            case 3:
                System.out.print("Enter the year: ");
                int yearMonth = scanner.nextInt();
                System.out.print("Enter the month (1-12): ");
                int month = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByYearAndMonth(yearMonth, month));
                orders = getDatabaseManager().getListOrdersByYearAndMonth(yearMonth, month);
                break;
            case 4:
                System.out.print("Enter the year: ");
                int yearDay = scanner.nextInt();
                System.out.print("Enter the month (1-12): ");
                int monthDay = scanner.nextInt();
                System.out.print("Enter the day: ");
                int day = scanner.nextInt();
                //printListOrders(getDatabaseManager().getListOrdersByDate(yearDay, monthDay, day));
                orders = getDatabaseManager().getListOrdersByDate(yearDay, monthDay, day);
                break;
            default:
                System.out.println("Invalid choice.");
        }
        bookRating(orders);
    }
    
    
    
    
    

    public void printListOrders(List<Order> orders) {
        //List<Order> orders = getDatabaseManager().getListOrders();
        System.out.println("----- List of orders -----");
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. %s sold to %s %s on %s%n ",
                    i + 1,
                    orders.get(i).getBook().getBookName(),
                    orders.get(i).getClient().getName(),
                    orders.get(i).getClient().getSurname(),

                    orders.get(i).getOrderDate()
                   
            );
            System.out.println( orders.get(i).getUnitsSold() + " pieces");
        }
    }


    public void bookRating(List <Order> orders) {
        //List<Order> orders = getDatabaseManager().getListOrders();
        Map<Book, Integer> bookRatingMap = new HashMap<>();
        int booksTotal = 0;

        for (Order order : orders) {

            Book book = order.getBook();
            int unitsSold = order.getUnitsSold();
            booksTotal += unitsSold;
            bookRatingMap.put(book, bookRatingMap.getOrDefault(book, 0) + unitsSold);
        }

        Map<Book, Integer> sortedBookRatingMap = bookRatingMap.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (e1, e2) -> e1,
                        LinkedHashMap::new
                ));

        int n = 1;
        double percentRough;
        double percentRounded;

        for (Map.Entry<Book, Integer> entry : sortedBookRatingMap.entrySet()) {

            percentRough = entry.getValue() * 100/ booksTotal;
            percentRounded = Math.floor(percentRough * 100) / 100;

            System.out.println(n + ". "
                    + entry.getKey().getBookName() 
                    + ": " + entry.getValue()+ " orders" 
                    +" ("+percentRounded+"%)");
            n++;
        }
    }
    
    public void clientRating(List <Order> orders) {
        //List<Order> orders = getDatabaseManager().getListOrders();
        List<User> usera = getDatabaseManager().getListUsers();
        Map<Client, Integer> clientRatingMap = new HashMap<>();
        int bookOrdersTotal = 0;
        for (Order order : orders) {
            Client client = order.getClient();
            int unitsSold = order.getUnitsSold();
            bookOrdersTotal += unitsSold;
            clientRatingMap.put(client, clientRatingMap.getOrDefault(client, 0) + unitsSold);
        }

        Map<Client, Integer> sortedClientRatingMap;
            sortedClientRatingMap = clientRatingMap.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (e1, e2) -> e1,
                            LinkedHashMap::new
                    ));

        double percentRough;
        double percentRounded;    
        int n = 1;
        for (Map.Entry<Client, Integer> entry : sortedClientRatingMap.entrySet()) {

            percentRough = entry.getValue() * 100/ bookOrdersTotal;
            percentRounded = Math.floor(percentRough * 100) / 100;
            System.out.println(n + ". " + entry.getKey().getName() + " " + entry.getKey().getSurname() + ": " + entry.getValue() + " books " + "("+percentRounded+"%)");

            n++;
    }

    }
    
    



    public void printListSoldBooks() {
        List<Order> orders = getDatabaseManager().getListOrders();
        System.out.println("----- List of all sold books -----");
        for (int i = 0; i < orders.size(); i++) {
            System.out.printf("%d. %s: %d units%n",
                    i + 1,
                    orders.get(i).getBook().getBookName(),
                    orders.get(i).getUnitsSold()
            );
        }
    }
    public void productSalesRating() {
        List<Order> orders = getDatabaseManager().getListOrders();
        List<Book> books = getDatabaseManager().getListBooks();
        System.out.println("----- Product Order Rating -----");
        bookManager.printListBooks();

        System.out.print("Enter the book number to display sales rating: ");
        int choice = InputProtection.intInput(1, books.size()) - 1;
        Book selectedBook = books.get(choice);

        double totalSales = 0;
        double totalRating = 0;

        for (Order order : orders) {
            if (order.getBook().equals(selectedBook)) {
                totalSales += order.getQuantity();
                totalRating += order.getBook().getOrderRating() * order.getQuantity();
            }
        }

        double averageRating = (totalSales > 0) ? totalRating / totalSales : 0;

        System.out.printf("Product: %s%nTotal Sales: %.2f%nAverage Sales Rating: %.2f%n",
                selectedBook.getBookName(), totalSales, averageRating);
    }
}


