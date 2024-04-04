/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;


import entity.Book;
import entity.Client;
import entity.Order;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class SaveManager {
    private final String BOOK_FILENAME = "books";
    private final String CLIENT_FILENAME = "clients";
    private final String ORDER_FILENAME = "orders";

    public List<Book> loadBooks() {
        List<Book> books = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(BOOK_FILENAME);
            ois = new ObjectInputStream(fis);
            books = (List<Book>) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.printf("File \"%s\" not found!%n", BOOK_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        } catch (ClassNotFoundException ex) {
            System.out.printf("Class \"%s\" not found!%n", BOOK_FILENAME);
        }
        return books;
    }

    public void saveBooks(List<Book> books) {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(BOOK_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(books);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.printf("File \"%s\" not found!%n", BOOK_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        }
    }

    public List<Client> loadClients() {
        List<Client> cllients = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(CLIENT_FILENAME);
            ois = new ObjectInputStream(fis);
            cllients = (List<Client>) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.printf("File \"%s\" not found!%n", CLIENT_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        } catch (ClassNotFoundException ex) {
            System.out.printf("Class \"%s\" not found!%n", CLIENT_FILENAME);
        }
        return cllients;
    }

    public void saveClient(List<Client> clients) {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(CLIENT_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(clients);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.printf("File \"%s\" not found!%n", CLIENT_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        }
    }
    
       public List<Order> loadOrders() {
        List<Order> order = new ArrayList<>();
        FileInputStream fis;
        ObjectInputStream ois;
        try {
            fis = new FileInputStream(ORDER_FILENAME);
            ois = new ObjectInputStream(fis);
            order = (List<Order>) ois.readObject();
        } catch (FileNotFoundException ex) {
            System.out.printf("File \"%s\" not found!%n", ORDER_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        } catch (ClassNotFoundException ex) {
            System.out.printf("Class \"%s\" not found!%n", ORDER_FILENAME);
        }
        return order;
    }

    public void saveSales(List<Order> orders) {
        ObjectOutputStream oos;
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(ORDER_FILENAME);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(orders);
            oos.flush();
        } catch (FileNotFoundException ex) {
            System.out.printf("File \"%s\" not found!%n", ORDER_FILENAME);
        } catch (IOException ex) {
            System.out.println("Error I/O!");
        }
    }
}
