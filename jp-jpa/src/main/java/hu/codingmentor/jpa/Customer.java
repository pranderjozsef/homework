package hu.codingmentor.jpa;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CUSTOMERS")
@NamedQuery(name = "findCustomerById",
        query = "SELECT c FROM Customer c WHERE c.customerId = :cid")
public class Customer extends Person implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "CUSTOMER_ID")
    private Long customerId;
    
    @OneToOne(mappedBy = "customer")
    private LoginInfo loginInfo;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();
    
    @OneToMany(mappedBy = "customer")
    private Set<Telephone> telephones = new HashSet<>();
    
    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "INTEREST")
    @Column(name = "INTEREST_NAME")
    private Collection <String> interest;

    public Customer() {
        //Empty
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public LoginInfo getLoginInfo() {
        return loginInfo;
    }

    public void setLoginInfo(LoginInfo loginInfo) {
        this.loginInfo = loginInfo;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

    public Set<Telephone> getTelephones() {
        return telephones;
    }

    public void setTelephones(Set<Telephone> telephones) {
        this.telephones = telephones;
    }

    public Collection<String> getInterest() {
        return interest;
    }

    public void setInterest(Collection<String> interest) {
        this.interest = interest;
    }

    @Override
    public String toString() {
        return "Customer{" + "customerId=" + customerId + ", firstName=" + super.getFirstName() + ", lastName=" + super.getLastName() + ", dateOfBirth=" 
                + super.getDateOfBirth() + ", email=" + super.getEmail() + ", interest=" + interest + '}';
    }
}
