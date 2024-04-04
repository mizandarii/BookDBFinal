/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managers;

import entity.Book;
import entity.Order;

import java.util.List;
import java.util.Scanner;
import tools.InputProtection;

/**
 *
 * @author admin
 */
public class BookManager {
    private final Scanner scanner;
    private final DatabaseManager databaseManager;
    
    public BookManager(Scanner scanner, DatabaseManager databaseManager) {
        this.scanner = scanner;
        this.databaseManager = databaseManager;
    }


    
    public DatabaseManager getDatabaseManager() {
        return databaseManager;
    }
    

    public void printListBooks() {
        int n = 0;
        System.out.println("----- List of all Books -----");
        List<Book> books = getDatabaseManager().getListBooks();
        for (int i = 0; i < books.size(); i++) {
            String yearString = Integer.toString(books.get(i).getYear());

            System.out.println(i + 1 +". " 
                    + "Name: "+books.get(i).getBookName() 
                    + " | Price: "+books.get(i).getPrice()
                    + " | ISBN: "+books.get(i).getIsbnCode()
                    + " | Author: "+ books.get(i).getAuthor()
                    + " | Year: "+ yearString
                    + " | Stock: "+ books.get(i).getQuantity());


        }
}
    public void setOrderRating(Book books, double rating) {
        books.setOrderRating(rating);
        System.out.println("Orders rating set successfully for " + books.getBookName() + ": " + rating);
    }
    
    public Book addBook() {
        System.out.println("----- Add a Book -----");
        Book book = new Book();
        System.out.print("Enter book name: ");
        book.setBookName(scanner.nextLine());

        System.out.print("Enter price in euros: ");
        double bookPrice = Double.parseDouble(scanner.nextLine());
        book.setPrice(bookPrice);


        System.out.print("Enter publishing year: ");
        int bookYear = Integer.parseInt(scanner.nextLine());
        book.setYear(bookYear);
        
        System.out.print("Enter publishing year: ");
        String ISBNCode = scanner.nextLine();
        book.setIsbnCode(ISBNCode);

        //scanner.nextLine();

        System.out.print("Enter author: ");
        String bookAuthor = scanner.nextLine();
        book.setAuthor(bookAuthor);

        System.out.print("Enter Quantity: ");
        int bookQuantity = Integer.parseInt(scanner.nextLine());
        book.setQuantity(bookQuantity);


        databaseManager.saveBook(book);
        return book;
   
}
    

    public void editBook() {
        List<Book> books = getDatabaseManager().getListBooks();
    
        System.out.println("----- Edit a Book -----");
        printListBooks();
        System.out.print("Enter the number of the book to edit: ");
        int bookChoice = InputProtection.intInput(1, books.size()) - 1;
        Book BookToEdit = books.get(bookChoice);



        System.out.println("Press: \n"
                + "1 to edit book name \n"
        + "2 to edit book publishing year \n"
        + "3 to edit book price \n"
        + "4 to edit book author \n"
        + "5 to edit book quantity \n"
        + "6 to edit book quantity \n");


        int task = InputProtection.intInput(1,6); 

        switch (task) {
            case 1:
                System.out.print("Enter new name: ");
                String newName = scanner.nextLine();
                if (!newName.isEmpty()) {
                BookToEdit.setBookName(newName);
                    }

            case 2:
                System.out.print("Enter new publishing year: ");
                int newYear = Integer.parseInt(scanner.nextLine());
                if (newYear >= 1440){
                    BookToEdit.setYear(newYear);
                }
                break;
            case 3:
                System.out.print("Enter new price: ");
                double newPrice = InputProtection.doubleInput(-1, Double.MAX_VALUE);
                if (newPrice >= 0) {
                BookToEdit.setPrice(newPrice);
                }
                break;
            case 4:
                System.out.print("Enter new author: ");
                String newAuthor = scanner.nextLine();
                if (!newAuthor.isEmpty()) {
                BookToEdit.setAuthor(newAuthor);
                }
            case 5:
                System.out.print("Enter new quantity: ");
                int newQuantity = Integer.parseInt(scanner.nextLine());
                if (newQuantity >= 0) {
                BookToEdit.setQuantity(newQuantity);
                }    
            case 6:
                System.out.print("Enter new ISBN: ");
                String newIsbn = scanner.nextLine();
                if (!newIsbn.isEmpty()) {
                BookToEdit.setIsbnCode(newIsbn);
                }    


            default:
                System.out.println("Invalid task number. Please select a task from the list above.");
        }

        databaseManager.saveBook(BookToEdit);
        System.out.println("Book edited successfully!");
        printListBooks();
        }
}
    
