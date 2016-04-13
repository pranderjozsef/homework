package hu.codingmentor.main;

import hu.codingmentor.jpa.Book;
import hu.codingmentor.jpa.CD;
import hu.codingmentor.jpa.Customer;
import hu.codingmentor.jpa.Item;
import hu.codingmentor.jpa.LoginInfo;
import hu.codingmentor.jpa.Order;
import hu.codingmentor.jpa.Telephone;
import hu.codingmentor.jpa.TelephoneType;
import hu.codingmentor.logger.MyFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.logging.ConsoleHandler;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class Main {
    private static final Logger LOGGER = Logger.getLogger(Main.class.getName());
    private static final String LINE = "\n============================================================" 
                                        + "============================================================"
                                        + "============================================================\n";

    
    private Main(){}
    
    public static void main(String[] args) {
        LogManager.getLogManager().reset();
        ConsoleHandler handler = new ConsoleHandler();
        handler.setFormatter(new MyFormatter());
        LOGGER.addHandler(handler);
        
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jp-jpa");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        
        Customer customer = creaCustomer(em, "Andras", "Berenyi", 1962, 9, 17, "berenyia@gmail.com", 
                            "Sport", "Music", "Movies", "Art");
        
        createTelephone(em, TelephoneType.LANDLINE, "06-96/123-456", customer);
        createTelephone(em, TelephoneType.MOBILE, "06-30/1234-567", customer);
        
        createLoginInfo(em, "AndrasB", "MatyasKiralyTer12", customer);
        
        Item item = createItem(em, "165-456-98", "This is an item.", 1700);
        
        Book book = createBook(em, "Originals", "978-81-7525-766-5", 2000, 500, "Random House");
        createBook(em, "Game of Thrones", "978-40-1276-510-7", 4000, 1200, "Pearson");
        
        CD cd = createCD(em, "Best of Instrumental Core", "978-81-7525", 1000, 14, "Ice Records");
        createCD(em, "Best of Eminem", "234-10-4726", 1200, 17, "Ice Records");
        
        createOrder(em, "1234-567", "Description for an order.", customer, item, book, cd);
        
        callFiveNamedQuery(em);

        tx.commit();
        em.close();
        emf.close();
    }
    
    public static Customer creaCustomer(EntityManager em, String firstName, String lastName, 
            int year, int month, int day, String email, String... interests){
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        
        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setDateOfBirth(calendar.getTime());
        customer.setEmail(email);
        customer.setInterest(new ArrayList<>(Arrays.asList(interests)));
        em.persist(customer);
        return customer;
    }
    
    public static Telephone createTelephone(EntityManager em, TelephoneType telephoneType, String telephoneNumber, Customer customer){
        Telephone telephone = new Telephone();
        telephone.setTelephoneType(telephoneType);
        telephone.setTelephoneNumber(telephoneNumber);
        telephone.setCustomer(customer);
        em.persist(telephone);
        return telephone;
    }
    
    public static LoginInfo createLoginInfo(EntityManager em, String loginName, String password, Customer customer){
        LoginInfo loginInfo = new LoginInfo();
        loginInfo.setLoginName(loginName);
        loginInfo.setPassword(password);
        loginInfo.setCustomer(customer);
        em.persist(loginInfo);
        return loginInfo;
    }
    
    public static Item createItem(EntityManager em, String title, String itemNumber, int price){
        Item item = new Item();
        item.setItemNumber(title);
        item.setItemNumber(itemNumber);
        item.setItemDescription("This is an item.");
        item.setPrice(price);
        em.persist(item);
        return item;
    }
    
    public static Book createBook(EntityManager em, String title, String itemNumber,
                        int price, int nbOfPage, String publisher){
        Book book = new Book();
        book.setTitle(title);
        book.setItemNumber(itemNumber);
        book.setItemDescription("Title of this book : " + book.getTitle());
        book.setPrice(price);
        book.setNbOfPage(nbOfPage);
        book.setPublisher(publisher);
        em.persist(book);
        return book;
    }
    
    public static CD createCD(EntityManager em, String title, String itemNumber,
                        int price, int numberOfTracks, String recordLabel){
        CD cd = new CD();
        cd.setTitle(title);
        cd.setItemNumber(itemNumber);
        cd.setItemDescription("Title of this cd : " + cd.getTitle());
        cd.setPrice(price);
        cd.setNumberOfTracks(numberOfTracks);
        cd.setRecordLabel(recordLabel);
        em.persist(cd);
        return cd;
    }
    
    public static Order createOrder(EntityManager em, String orderNumber, String orderDescription, Customer customer, Item... items){
        Order order = new Order();
        order.setOrderNumber(orderNumber);
        order.setOrderDescription(orderDescription);
        order.setCustomer(customer);
        order.setItems(new ArrayList<>(Arrays.asList(items)));
        em.persist(order);
        return order;
    }
    
    public static void callFiveNamedQuery(EntityManager em){
        TypedQuery<LoginInfo> customerLoginName = em.createNamedQuery("findCustomerByLoginName", LoginInfo.class);
        customerLoginName.setParameter("ln", "AndrasB");
        LOGGER.info("\n" + LINE + "Customer by login name:" + LINE + customerLoginName.getSingleResult() + LINE + "\n");
        
        TypedQuery<Telephone> phoneNumber = em.createNamedQuery("findCustomerByPhoneNumber", Telephone.class);
        phoneNumber.setParameter("tn", "06-30/1234-567");
        LOGGER.info("\n" + LINE + "Customer by phone number:" + LINE + phoneNumber.getSingleResult() + LINE + "\n");
        
        TypedQuery<Book> bookPublisher = em.createNamedQuery("findBookByPublisher", Book.class);
        bookPublisher.setParameter("p", "Random House");
        LOGGER.info("\n" + LINE + "Books by publisher:" + LINE);
        bookPublisher.getResultList().forEach(x -> LOGGER.info(x.toString() + LINE));
        
        TypedQuery<CD> cdRecordLabel = em.createNamedQuery("findCDByRecordLabel", CD.class);
        cdRecordLabel.setParameter("rl", "Ice Records");
        LOGGER.info("\n\n\n" + LINE + "CDs by record label:" + LINE);
        cdRecordLabel.getResultList().forEach(x -> LOGGER.info(x.toString() + LINE));
        
        TypedQuery<Item> itemPrice = em.createNamedQuery("findItemByPrice", Item.class);
        itemPrice.setParameter("p", 2100);
        LOGGER.info("\n\n\n" + LINE + "Items by price:" + LINE);
        itemPrice.getResultList().forEach(x -> LOGGER.info(x.toString() + LINE));       
    }
}
