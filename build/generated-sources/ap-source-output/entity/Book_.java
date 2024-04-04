package entity;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-09T19:59:38")
@StaticMetamodel(Book.class)
public class Book_ { 

    public static volatile SingularAttribute<Book, String> isbnCode;
    public static volatile SingularAttribute<Book, Integer> quantity;
    public static volatile SingularAttribute<Book, Integer> year;
    public static volatile SingularAttribute<Book, String> author;
    public static volatile SingularAttribute<Book, Double> price;
    public static volatile SingularAttribute<Book, Long> id;
    public static volatile SingularAttribute<Book, Double> orderRating;
    public static volatile SingularAttribute<Book, String> bookName;

}