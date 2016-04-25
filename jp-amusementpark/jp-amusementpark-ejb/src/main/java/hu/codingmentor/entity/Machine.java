package hu.codingmentor.entity;

import hu.codingmentor.enums.MachineType;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@NamedQuery(name = "findMachinesByParkId",
    query = "SELECT m FROM Machine m WHERE m.amusementPark.parkId = :parkId")
public class Machine implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "MACHINE_ID")
    private Long machineId;
    
    private String fantasyName;
    
    private Long price;
    
    private Long extent;
    
    @Column(name = "TICKET_PRICE")
    private Integer ticketPrice;
    
    private Integer capacity;
    
    @Enumerated(EnumType.STRING)
    private MachineType machineType;
    
    @Column(name = "AGE_REQUIREMENT")
    private Integer ageRequirement;
    
    @ManyToOne()
    @JoinColumn(name = "PARK_ID")
    private AmusementPark amusementPark;
    
    public Machine() {
        //
    }

    public Long getMachineId() {
        return machineId;
    }

    public void setMachineId(Long machineId) {
        this.machineId = machineId;
    }

    public String getFantasyName() {
        return fantasyName;
    }

    public void setFantasyName(String fantasyName) {
        this.fantasyName = fantasyName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
    
    public Long getExtent() {
        return extent;
    }

    public void setExtent(Long extent) {
        this.extent = extent;
    }

    public Integer getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public MachineType getMachineType() {
        return machineType;
    }

    public void setMachineType(MachineType machineType) {
        this.machineType = machineType;
    }

    public Integer getAgeRequirement() {
        return ageRequirement;
    }

    public void setAgeRequirement(Integer ageRequirement) {
        this.ageRequirement = ageRequirement;
    }

    @XmlTransient
    public AmusementPark getAmusementPark() {
        return amusementPark;
    }

    public void setAmusementPark(AmusementPark amusementPark) {
        this.amusementPark = amusementPark;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.machineId);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Machine other = (Machine) obj;
        if (!Objects.equals(this.machineId, other.machineId)) {
            return false;
        }
        return true;
    }  
}
