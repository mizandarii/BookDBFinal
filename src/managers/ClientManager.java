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
import tools.InputProtection;

/**
 *
 * @author admin
 */
public class ClientManager {
    private final Scanner scanner;
    private final DatabaseManager databaseManager;
    
    public ClientManager(Scanner scanner,DatabaseManager databaseManager) {
        this.scanner = scanner;
        this.databaseManager = databaseManager;
    }

    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    
    public Client returnNewClient(){
        
        System.out.println("----------Add a client----------");
        
        Client client = new Client();
        
        System.out.println("Name");
        client.setName(scanner.nextLine());
        
        System.out.println("Surname");
        client.setSurname(scanner.nextLine());
        
        System.out.println("Phone number");
        client.setPhone(scanner.nextLine());
        
        System.out.println("City");
        client.setCity(scanner.nextLine());
        
        System.out.println("Balance");
        double clientBalance = Double.parseDouble(scanner.nextLine());
        client.setBalance(clientBalance);
       
        //scanner.nextLine();
        
        
        return client;
        
    }
    
    
    
    
    
    
    
    public void printListClients() {
        List<User> users = getDatabaseManager().getListUsers(); 
        System.out.println("----- List of clients -----");
        for (int i = 0; i < users.size(); i++) {
            
            User user = users.get(i);
            double balanceRough = user.getClient().getBalance();
            double balanceRounded = Math.floor(balanceRough * 100) / 100;
            System.out.println(i+1 + 
                    ". Name: " + user.getClient().getName() + 
                    " | Surname: "+user.getClient().getSurname() +
                    " | City: "+ user.getClient().getCity() +
                    " | Phone number: "+ user.getClient().getPhone() +
                    " | Balance (EUR): "+ balanceRounded);
            
        }
    }
    
    public void editClient() {
        List <User> clients = getDatabaseManager().getListUsers();
        System.out.println("----- Edit a Client -----");
        printListClients();

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
        
         printListClients();
        
        System.out.println("----- Enter client number -----");
        
        int clientNumber = Integer.parseInt(scanner.nextLine());
        clientNumber-=1;
        User user = users.get(clientNumber);
        
        System.out.println("----- Enter amount of money to add -----");
        double addedMoney = Double.parseDouble(scanner.nextLine());
        user.getClient().setBalance(user.getClient().getBalance() + addedMoney);


        System.out.println("Client balance updated!");
        return user;
    }
    

}
