package managers;

import entity.Book;
import entity.Order;
import entity.OrderHistory;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class DatabaseManager {
    private EntityManagerFactory emf;
    private EntityManager em;

    public DatabaseManager() {
        emf = Persistence.createEntityManagerFactory("BookStoreDatabasePU");
        em = emf.createEntityManager();
    }

    public void closeEntityManager() {
        if (em != null && em.isOpen()) {
            em.close();
        }

        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }

    public void saveBook(Book book) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (book.getId() == null) {
                em.persist(book);
            } else {
                em.merge(book);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            //closeEntityManager();
        }
    }

    public void saveUser(User user) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();
            if (user.getId() == null) {
                em.persist(user);
            } else {
                em.merge(user);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        } finally {
            //closeEntityManager();
        }
    }

    public List<Book> getListBooks() {
        return em.createQuery("SELECT book FROM Book book").getResultList();
    }

    public List<User> getListUsers() {
        return em.createQuery("SELECT user FROM User user").getResultList();
    }
    
    public List<Order> getListOrders() {
        return em.createQuery("SELECT order FROM Order order").getResultList();
    }    
    
    
    public List<Order> getListOrdersByYear(int year) {
        return em.createQuery("SELECT order FROM Order order WHERE FUNCTION('YEAR', order.orderDate) = :year")
                .setParameter("year", year)
                .getResultList();
    }

    public List<Order> getListOrdersByYearAndMonth(int year, int month) {
    return em.createQuery("SELECT order FROM Order order WHERE FUNCTION('YEAR', order.orderDate) = :year AND FUNCTION('MONTH', order.orderDate) = :month")
            .setParameter("year", year)
            .setParameter("month", month)
            .getResultList();
    }
    
    
public List<Order> getListOrdersByDate(int year, int month, int day) {
    return em.createQuery("SELECT order FROM Order order WHERE EXTRACT(YEAR FROM order.orderDate) = :year AND EXTRACT(MONTH FROM order.orderDate) = :month AND EXTRACT(DAY FROM order.orderDate) = :day")
            .setParameter("year", year)
            .setParameter("month", month)
            .setParameter("day", day)
            .getResultList();
}

    
    




    /**
    public List<OrderHistory> getReadingBooks(){
        return  em.createQuery("SELECT history FROM History history WHERE history.returnBook=null")
                .getResultList();
    }**/

    public User authorization(String login, String password) {
        try {
            return (User) em.createQuery("SELECT user FROM User user WHERE user.login = :login AND user.password = :password")
                    .setParameter("login", login)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

    public Book getBook(Long id) {
        try {
            return em.find(Book.class, id);
        } catch (Exception e) {
            return null;
        }
    }
    public User getUser(Long id) {
        try {
            return em.find(User.class,id);
        } catch (Exception e) {
            return null;
        }
    }

    public void saveOrder(Order order) {
        try {
            em.getTransaction().begin();
            if(order.getId() == null){
                em.persist(order);
            }else{
                em.merge(order);
            }
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    OrderHistory getHistory(Long id) {
        try {
            return em.find(OrderHistory.class,id);
        } catch (Exception e) {
            return null;
        }
    }

    List<OrderHistory> getListHistories() {
        return em.createQuery("SELECT h FROM OrderHistory h")
                .getResultList();
    }

    
}