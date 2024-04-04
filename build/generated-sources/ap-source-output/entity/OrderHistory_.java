package entity;

import entity.Book;
import entity.Client;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2024-03-09T19:59:38")
@StaticMetamodel(OrderHistory.class)
public class OrderHistory_ { 

    public static volatile SingularAttribute<OrderHistory, Book> book;
    public static volatile SingularAttribute<OrderHistory, Client> client;
    public static volatile SingularAttribute<OrderHistory, Long> id;
    public static volatile SingularAttribute<OrderHistory, Date> orderDate;

}