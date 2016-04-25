package hu.codingmentor.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PostUpdate;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQuery(name = "findAddressByParkId",
    query = "SELECT a.address FROM AmusementPark a WHERE a.parkId = :parkId")
public class AmusementPark implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "PARK_ID")
    private Long parkId;
    
    private String name;
    
    private Integer ticketPrice;
    
    private Long capital;
    
    @Column(name = "TOTAL_AREA")
    private Long totalArea;
    
    @OneToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;
    
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, 
    mappedBy = "amusementPark")
    private Set<Machine> machines = new HashSet<>();
    
    @Transient
    private Long freeArea;
    
    public AmusementPark() {
        //
    }
    
    @PostLoad
    @PostPersist
    @PostUpdate
    public void calculateFreeArea(){
        freeArea = getTotalArea();
        
        for(Machine m : machines){
            freeArea -= m.getExtent();
        }
    }
    
    public Long getParkId() {
        return parkId;
    }

    public void setParkId(Long parkId) {
        this.parkId = parkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }
    
    public Long getCapital() {
        return capital;
    }

    public void setCapital(Long capital) {
        this.capital = capital;
    }

    public Long getTotalArea() {
        return totalArea;
    }

    public void setTotalArea(Long totalArea) {
        this.totalArea = totalArea;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Set<Machine> getMachines() {
        return machines;
    }

    public void setMachines(Set<Machine> machines) {
        this.machines = machines;
    }
    
    @XmlTransient
    public Long getFreeArea() {
        return freeArea;
    }

    public void setFreeArea(Long freeArea) {
        this.freeArea = freeArea;
    }
}
