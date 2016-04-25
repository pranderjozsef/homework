package hu.codingmentor.entity;

import hu.codingmentor.adapter.DateAdapter;
import hu.codingmentor.adapter.DateTimeAdapter;
import hu.codingmentor.enums.State;
import java.io.Serializable;
import java.util.Calendar;
import static java.util.Calendar.DAY_OF_YEAR;
import static java.util.Calendar.YEAR;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@NamedQueries({
    @NamedQuery(name = "findVisitorsByMachineId",
            query = "SELECT v FROM Visitor v WHERE v.machine.machineId = :machineId"),
    @NamedQuery(name = "numberOfRestingVisitorByParkId",
        query = "SELECT COUNT(v) FROM Visitor v WHERE v.amusementPark.parkId = :parkId AND v.state LIKE 'REST'")
})
public class Visitor implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "VISITOR_ID")
    private Long visitorId;
    
    @Enumerated(EnumType.STRING)
    private State state;
    
    @Column(name = "SPENDING_MONEY")
    private Integer spendingMoney;
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @Column(name = "TIME_OF_ENTRY")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfEntry;
    
    @XmlJavaTypeAdapter(DateAdapter.class)
    @Column(name = "DATE_OF_BIRTH")
    @Temporal(TemporalType.DATE)    
    private Date dateOfBirth;
    
    private Boolean active;
    
    @ManyToOne()
    @JoinColumn(name = "PARK_ID")
    private AmusementPark amusementPark;
    
    @ManyToOne()
    @JoinColumn(name = "MACHINE_ID")
    private Machine machine;
    
    @Transient
    private Integer age;
    
    public Visitor() {
        //
    }
    
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateAge(){
        if (dateOfBirth == null) {
               age = null;
               return;
        }
        
        Calendar birth = new GregorianCalendar();
        birth.setTime(dateOfBirth);
        Calendar now = new GregorianCalendar();
        now.setTime(timeOfEntry);
        int adjust = 0;
        if (now.get(DAY_OF_YEAR) - birth.get(DAY_OF_YEAR) < 0) {
                adjust = -1;
        }
        age = now.get(YEAR) - birth.get(YEAR) + adjust;
    }               

    public Long getVisitorId() {
        return visitorId;
    }

    public void setVisitorId(Long visitorId) {
        this.visitorId = visitorId;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Integer getSpendingMoney() {
        return spendingMoney;
    }

    public void setSpendingMoney(Integer spendingMoney) {
        this.spendingMoney = spendingMoney;
    }

    public Date getTimeOfEntry() {
        return timeOfEntry;
    }

    public void setTimeOfEntry(Date timeOfEntry) {
        this.timeOfEntry = timeOfEntry;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
    
    @XmlTransient
    public Machine getMachine() {
        return machine;
    }

    public void setMachine(Machine machine) {
        this.machine = machine;
    }
    
    @XmlTransient
    public AmusementPark getAmusementPark() {
        return amusementPark;
    }

    public void setAmusementPark(AmusementPark amusementPark) {
        this.amusementPark = amusementPark;
    }  
}
