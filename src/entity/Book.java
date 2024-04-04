/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author kristiinaparri
 */
@Entity
@Table(name="books")
public class Book implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    
    private String bookName;
    private String isbnCode;
    private String author;
    private int year; 
    private double price;
    private int quantity;
    private double orderRating;
    
    public Book(){
        
    }
    
    public Book(Long id, String bookName, String isbnCode, String author, int year, double price, int quantity, double orderRating){
        this.id = id;
        this.bookName = bookName;
        this.isbnCode = isbnCode;
        this.author = author;
        this.year = year;
        this.price = price;
        this.quantity = quantity;
        this.orderRating = orderRating;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setIsbnCode(String isbnCode) {
        this.isbnCode = isbnCode;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public String getBookName() {
        return bookName;
    }

    public String getIsbnCode() {
        return isbnCode;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getYear() {
        return year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getOrderRating() {
        return orderRating;
    }

    public void setOrderRating(double orderRating) {
        this.orderRating = orderRating;
    }
    
    

    @Override
    public String toString() {
        return  "id: " + id + "\n" 
                + "book name: " + bookName + "\n" 
                + "isbn code: " + isbnCode + "\n" 
                + "author: " + author + "\n" 
                + "published: " + year + "\n" 
                + "price: " + price + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.id);
        hash = 29 * hash + Objects.hashCode(this.bookName);
        hash = 29 * hash + Objects.hashCode(this.isbnCode);
        hash = 29 * hash + Objects.hashCode(this.author);
        hash = 29 * hash + Objects.hashCode(this.year);
        hash = 29 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Book other = (Book) obj;
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.bookName, other.bookName)) {
            return false;
        }
        if (!Objects.equals(this.isbnCode, other.isbnCode)) {
            return false;
        }
        if (!Objects.equals(this.author, other.author)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return Objects.equals(this.year, other.year);
    }


    
    /** 
     * public static String printAllBooks(){
        for (Book book : books){
            
        }
    }
    **/
    
}