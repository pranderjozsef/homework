package hu.codingmentor.jpa;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "ITEMS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NamedQuery(name = "findItemByPrice",
        query = "SELECT i FROM Item i WHERE i.price < :p")
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "ITEM_ID")
    private Long itemId;
    
    private String title;
    
    @Column(name = "ITEM_NUMBER")
    private String itemNumber;
    
    @Column(name = "ITEM_DESCRIPTION")
    private String itemDescription;
    
    private Integer price;
    
    @ManyToMany(mappedBy = "items")
    private Collection<Order> orders;

    public Item() {
        //Empty
    }
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }
    
    public String getItemNumber() {
        return itemNumber;
    }

    public void setItemNumber(String itemNumber) {
        this.itemNumber = itemNumber;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
    
    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    @Override
    public String toString() {
        return "Item{" + "itemId=" + itemId + ", title=" + title + ", itemNumber=" + 
                itemNumber + ", itemDescription=" + itemDescription + ", price=" + price;
    }
}
