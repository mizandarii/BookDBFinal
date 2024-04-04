package entity;

import entity.Book;
import entity.Client;
import entity.User;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-09T19:59:38")
@StaticMetamodel(Order.class)
public class Order_ { 

    public static volatile SingularAttribute<Order, Integer> quantity;
    public static volatile SingularAttribute<Order, Book> book;
    public static volatile SingularAttribute<Order, Integer> unitsSold;
    public static volatile SingularAttribute<Order, Client> client;
    public static volatile SingularAttribute<Order, Long> id;
    public static volatile SingularAttribute<Order, User> user;
    public static volatile SingularAttribute<Order, Date> orderDate;

}