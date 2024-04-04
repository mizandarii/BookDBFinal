package bookstoredatabase;

import managers.SaveManager;
import managers.*;
import managers.ClientManager;
import managers.BookManager;
import entity.Book;
import entity.Order;
import entity.Client;
import entity.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;
import managers.DatabaseManager;
import managers.SaleManager;
import managers.UserManager;
import tools.InputProtection;
import tools.PassEncrypt;

public class App {
    
    public static enum ROLES {ADMINISTRATOR, MANAGER, USER};
    //public static Client client;
    public static User user;
    private final Scanner scanner; 
    //private List<Book> books;
    //private List<Client> clients;
    //private List<Order> orders;
    private final BookManager bookManager;
    //private final ClientManager clientManager;
    private final OrderManager orderManager;
    private final UserManager userManager;
    //private final SaveManager saveManager;
    private final DatabaseManager databaseManager;
    private final SaleManager saleManager;

    public App() {
        this.scanner = new Scanner(System.in);
        //.books = saveManager.loadBooks();
        //this.clients = saveManager.loadClients();
        //this.orders = saveManager.loadOrders();
        //this.userManager = new UserManager();
        
        this.databaseManager = new DatabaseManager();
        this.bookManager = new BookManager(scanner, databaseManager);
        this.userManager = new UserManager(scanner, databaseManager);
        this.orderManager = new OrderManager(scanner, userManager, bookManager, databaseManager);
        this.saleManager = new SaleManager(2024, 4, 1, 0, 0);
    }

    public void run() {
        checkAdmin();
        System.out.println("If you have a login and password press y, otherwise press n");
        String word = scanner.nextLine();
        if("n".equals(word)){
            databaseManager.saveUser(userManager.addUser());
        }
        for(int n=0;n<3;n++){
            System.out.print("Please enter your login: ");
            String login = scanner.nextLine();
            System.out.print("Please enter your password: ");
            String password = scanner.nextLine().trim();
            PassEncrypt pe = new PassEncrypt();
            String encryptPassword = pe.getEncryptPassword(password, pe.getSalt());
            App.user = databaseManager.authorization(login, encryptPassword);
            if(App.user != null){
                break;
            }
            System.out.println("Invalid login or password");
        }
        if(App.user == null) return;
        System.out.printf("Hello %s %s, welcome to the library%n",App.user.getClient().getName(),App.user.getClient().getSurname());

        boolean repeat = true;
        System.out.println("------- Book store -------");
        System.out.println("Big sale on April 1st");
        //saleManager.printCountdown();

        do {
            saleManager.printCountdown();
            System.out.println("List of tasks:");
            System.out.println("0. Exit");
            System.out.println("1. Add a new book");
            System.out.println("2. Display all books");
            System.out.println("3. Add a new client");
            System.out.println("4. Display all clients");
            System.out.println("5. Sell a book");
            System.out.println("6. Display all orders (all time/year/month/day)");
            System.out.println("7. Books rating (all time/year/month/day)");
            System.out.println("8. Edit a book");
            System.out.println("9. Edit a client");
            System.out.println("10. Add money to a client's balance");
            System.out.println("11. Clients rating (all time/year/month/day)");

            
            int task = InputProtection.intInput(0,11); 
            System.out.printf("You select task %d, for exit press \"0\", to continue press \"1\": ",task);
            int toContinue = InputProtection.intInput(0,1);
            
            if(toContinue == 0) continue;
            switch (task) {
                case 0:
                    repeat = false;
                    break;
                case 1:
                    if(!App.user.getRoles().contains(App.ROLES.MANAGER.toString())){
                        System.out.println("No permission");
                        break;
                    }
                    //this.books.add(bookManager.addBook());
                    bookManager.addBook();
                   
                    break;
                case 2:
                    bookManager.printListBooks();
                    
                    break;
                case 3:
                    //this.clients.add(clientManager.addClient());
                    
                    if(!App.user.getRoles().contains(App.ROLES.MANAGER.toString())){
                        System.out.println("No permission");
                        break;
                    }
                    databaseManager.saveUser(userManager.addUser());
                    break;
                case 4:
                    userManager.printListUsers(databaseManager.getListUsers());
                    break;
                case 5:
                    //this.orders.add(orderManager.makeOrder(books, clients));

                    databaseManager.saveOrder(orderManager.makeOrder());
                    break;
                case 6:
                    //orderManager.printListSoldBooks(orders);
                    orderManager.printListOrdersFiltered();
                    break;
                case 7:
                    orderManager.printListBooksFiltered();
                    break;
                case 8: 
                    
                    if(!App.user.getRoles().contains(App.ROLES.MANAGER.toString())){
                        System.out.println("No permission");
                        break;
                    }
                    bookManager.editBook();

                    break;
                case 9:
                    
                    if(!App.user.getRoles().contains(App.ROLES.MANAGER.toString())){
                        System.out.println("No permission");
                        break;
                    }
                    userManager.editClient();

                    break;
                case 10:
                    userManager.addMoney();
                    //saveManager.saveClient(clients);
                case 11:
                    //orderManager.clientRating();
                    orderManager.printListClientsFiltered();

                    break; 
                  
                default:
                    System.out.println("Invalid task number. Please select a task from the list above.");
            }
            //saleManager.printCountdown();
            System.out.println("-----------------------");
        }
        while (repeat);
        System.out.println("Till next time");
        databaseManager.closeEntityManager();
    }
    
        private void checkAdmin() {
        if(!(databaseManager.getListUsers().size()>0)){
            User admin = new User();
            admin.setLogin("admin");
            PassEncrypt pe =  new PassEncrypt();
            admin.setPassword(pe.getEncryptPassword("12345", pe.getSalt()));
            admin.getRoles().add(App.ROLES.ADMINISTRATOR.toString());
            admin.getRoles().add(App.ROLES.MANAGER.toString());
            admin.getRoles().add(App.ROLES.USER.toString());
            Client client = new Client();
            client.setName("Kristiina");
            client.setSurname("Parri");
            client.setPhone("56686291");
            admin.setClient(client);
            databaseManager.saveUser(admin);
        }
}
}