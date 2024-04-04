/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Client;
import entity.User;
import java.util.List;
import java.util.Scanner;
import bookstoredatabase.App;
import tools.InputProtection;
import tools.PassEncrypt;

/**
 *
 * @author admin
 */
public class UserManager {
    private final Scanner scanner;
    private final DatabaseManager databaseManager;
    
    public UserManager(Scanner scanner,DatabaseManager databaseManager) {
        this.scanner = scanner;
        this.databaseManager = databaseManager;
    }

    public UserManager() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    
    public User addUser() {
        
        Client client = new Client();
        System.out.println("----- Add a client -----");
        System.out.print("Firstname: ");
        client.setName(scanner.nextLine());
        System.out.print("Lastname: ");
        client.setSurname(scanner.nextLine());
        System.out.print("Phone: ");
        
        System.out.print("City: ");
        String clientCity = scanner.nextLine();
        client.setCity(clientCity);
        
        System.out.print("Balance: "); 
        Double clientBalance = scanner.nextDouble();
        client.setBalance(clientBalance);
        
        client.setPhone(scanner.nextLine());
        User user = new User();
        System.out.print("Login: ");
        user.setLogin(scanner.nextLine());
        System.out.print("Password: ");
        PassEncrypt pe = new PassEncrypt();
        user.setPassword(pe.getEncryptPassword(scanner.nextLine().trim(),pe.getSalt()));
        
        user.setClient(client);
        user.getRoles().add(App.ROLES.USER.toString());
        System.out.println("New client added!");
        return user;
    }

    
   
    
    
    public void printListUsers(List<User> users) {
        System.out.println("----- List of all clients -----");
        //List<User> users = getDatabaseManager().getListUsers();
        
        for (int i = 0; i < users.size(); i++) {
            double moneyBalance = users.get(i).getClient().getBalance();
            System.out.printf("%d. %s %s. Login: %s (balance: %s)%n",
                    users.get(i).getId(),
                    users.get(i).getClient().getName(),
                    users.get(i).getClient().getSurname(),
                    users.get(i).getLogin(),
                    //users.get(i).getClient().getPhone()
                    Math.round(moneyBalance * 100.0) / 100.0

            );
        }
    }

    public void changeRole() {
        //Выводим список пользователей и выбираем пользователя
        //Выводим список ролей и выбираем роль
        //Выводим список действий и выбираем действие
        printListUsers(getDatabaseManager().getListUsers());
        System.out.println("Pick a user: ");
        int idUser = InputProtection.intInput(1, null);
        for (int i = 0; i < App.ROLES.values().length; i++) {
            System.out.printf("%d %s%n", i+1, App.ROLES.values()[i].toString());
        }
        System.out.println("Pick a role: ");
        int numRole = InputProtection.intInput(1, 3);
        System.out.println("Pick an action: ");
        System.out.println("1 - Add a role");
        System.out.println("2 - Delete a role");
        int action = InputProtection.intInput(1, 2);
        if(action == 1){
            this.addRole(idUser, numRole);
        }else if(action == 2){
            this.removeRole(idUser, numRole);
        }
        
    }

    private void addRole(int idUser, int numRole) {
        User user = getDatabaseManager().getUser((long)idUser);
        String role = App.ROLES.values()[numRole-1].toString();
        if(!user.getRoles().contains(role)){
            user.getRoles().add(role);
            getDatabaseManager().saveUser(user);
            System.out.println("Role added");
            if(App.user.getId().equals(user.getId())){
                App.user = user;
            }
        }else{
            System.out.printf("Role %s  already belongs to user",role);
        }
        
    }

    private void removeRole(int idUser, int numRole) {
        User user = getDatabaseManager().getUser((long)idUser);
        if(user.getLogin().equals("admin")){
            System.out.println("Change is not possible");
            return;
        }
        String role = App.ROLES.values()[numRole-1].toString();
        if(user.getRoles().contains(role)){
            user.getRoles().remove(role);
            getDatabaseManager().saveUser(user);
            if(App.user.getId().equals(user.getId())){
                App.user = user;
            }
            System.out.println("Role deleted");
        }else{
            System.out.printf("Role %s  doesn't belong to user",role);
        }
    }
    
    
    public void editClient() {
        List <User> clients = getDatabaseManager().getListUsers();
        System.out.println("----- Edit a Client -----");
        printListUsers(getDatabaseManager().getListUsers());

        System.out.print("Enter the number of the client to edit: ");
        int clientIndex = Integer.parseInt(scanner.nextLine()) - 1;
        
        System.out.println("Press: \n"
                + "1 to edit client's name \n"
        + "2 to edit last name \n"
        + "3 to edit phone number \n"
        + "4 to edit city \n"
        + "5 to edit balance \n");

    
    int task = InputProtection.intInput(1,5); 
    User user = clients.get(clientIndex);
    switch (task) {
                case 1:
                    System.out.print("New Firstname: ");
        String newFirstname = scanner.nextLine();
        if (!newFirstname.isEmpty()) {
            user.getClient().setName(newFirstname);
            break;
        }
                        

                case 2:
                    System.out.print("New Surname: ");
        String newSurname = scanner.nextLine(); 
        if (!newSurname.isEmpty()) {
            user.getClient().setSurname(newSurname);
        }
                    break;
                case 3:
                   System.out.print("New Phone: ");
        String newPhone = scanner.nextLine();
        if (!newPhone.isEmpty()) {
            user.getClient().setPhone(newPhone);
        }
                    break;
                case 4:
                    System.out.print("New City: ");
        String newCity = scanner.nextLine();
        if (!newCity.isEmpty()) {
            user.getClient().setCity(newCity);
            break;
        }
        
                case 5:
                        System.out.print("New Balance: ");
        double newBalance;
        newBalance = Double.parseDouble(scanner.nextLine());
        if (newBalance >= 0){
            user.getClient().setBalance(newBalance);
            break;
        }        

                  
                default:
                    System.out.println("Invalid task number. Please select a task from the list above.");
            }
        
        if (clientIndex < 0 || clientIndex >= clients.size()) {
            System.out.println("Invalid client selection.");
            return;
        }
        
        databaseManager.saveUser(user);
        System.out.println("Client updated!");
    }
        
    public Client addClient() {
        Client client = new Client();
        System.out.println("----- Add a client -----");
        
        System.out.print("First name: ");
        String clientName = scanner.nextLine();
        client.setName(clientName);

        System.out.print("Last name: ");
        String clientSurname = scanner.nextLine();
        client.setSurname(clientSurname);

        System.out.print("Phone: ");
        String clientPhone = scanner.nextLine();
        client.setPhone(clientPhone);
        
        System.out.print("City: ");
        String clientCity = scanner.nextLine();
        client.setCity(clientCity);
        
        System.out.print("Balance: "); 
        Double clientBalance = scanner.nextDouble();
        client.setBalance(clientBalance);


        System.out.println("New client added!");
        return client;
    }
    
    public User addMoney() {
        
        List <User> users = getDatabaseManager().getListUsers();
        
         printListUsers(getDatabaseManager().getListUsers());
        
        System.out.println("----- Enter client number -----");
        
        int clientNumber = Integer.parseInt(scanner.nextLine());
        clientNumber-=1;
        User user = users.get(clientNumber);
        
        System.out.println("----- Enter amount of money to add -----");
        double addedMoney = Double.parseDouble(scanner.nextLine());
        user.getClient().setBalance(user.getClient().getBalance() + addedMoney);


        System.out.println("Client balance updated!");
        databaseManager.saveUser(user);
        return user;
    }
}
