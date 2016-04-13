package hu.codingmentor.jpa;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LOGIN_INFO")
@NamedQuery(name = "findCustomerByLoginName",
        query = "SELECT l FROM LoginInfo l WHERE l.loginName = :ln")
public class LoginInfo implements Serializable  {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE) 
    @Column(name = "LOGIN_INFO_ID")
    private Long loginInfoId;
    
    @Column(name = "LOGIN_NAME")
    private String loginName;
    
    private String password;
    
    @OneToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    public LoginInfo() {
        //Empty
    }
    
    public Long getLoginInfoId() {
        return loginInfoId;
    }

    public void setLoginInfoId(Long loginInfoId) {
        this.loginInfoId = loginInfoId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String userName) {
        this.loginName = userName;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "LoginInfo{" + "loginInfoId=" + loginInfoId + ", loginName=" + loginName + ", password=" + password + ", customer=" + customer + '}';
    }
}
