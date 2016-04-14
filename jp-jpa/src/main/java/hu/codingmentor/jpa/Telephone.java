package hu.codingmentor.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findPhoneByType",
        query = "SELECT t FROM Telephone t WHERE t.telephoneType = :tt")
public class Telephone implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name = "TELEPHONE_ID")
    private Long telephoneId;
    
    @Column(name = "TELEPHONE_NUMBER")
    private String telephoneNumber;
    
    @Enumerated(EnumType.STRING)
    private TelephoneType telephoneType;
    
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public Telephone() {
        //Empty
    }

    public Long getTelephoneId() {
        return telephoneId;
    }

    public void setTelephoneId(Long telephoneId) {
        this.telephoneId = telephoneId;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public TelephoneType getTelephoneType() {
        return telephoneType;
    }

    public void setTelephoneType(TelephoneType telephoneType) {
        this.telephoneType = telephoneType;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Telephone{" + "telephoneId=" + telephoneId + ", telephoneNumber=" + telephoneNumber + ", telephoneType=" + telephoneType + '}';
    }
    
}
