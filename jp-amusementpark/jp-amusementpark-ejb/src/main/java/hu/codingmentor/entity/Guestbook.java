package hu.codingmentor.entity;

import hu.codingmentor.adapter.DateTimeAdapter;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

@Entity
@NamedQuery(name = "findWritingsByParkIdAndVisitorId",
            query = "SELECT g FROM Guestbook g WHERE g.amusementPark.parkId = :parkId AND g.visitor.visitorId = :visitorId")
public class Guestbook implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "GUESTBOOK_ID")
    private Long guestbookId;
    
    @XmlJavaTypeAdapter(DateTimeAdapter.class)
    @Column(name = "TIME_OF_ENTER")
    @Temporal(TemporalType.TIMESTAMP)
    private Date timeOfWrite;
    
    private String text;
    
    @ManyToOne()
    @JoinColumn(name = "PARK_ID")
    private AmusementPark amusementPark;
    
    @ManyToOne()
    @JoinColumn(name = "VISITOR_ID")
    private Visitor visitor;

    
    public Guestbook() {
        //
    }

    public Long getGuestbookId() {
        return guestbookId;
    }

    public void setGuestbookId(Long guestbookId) {
        this.guestbookId = guestbookId;
    }

    public Date getTimeOfWrite() {
        return timeOfWrite;
    }

    public void setTimeOfWrite(Date timeOfWrite) {
        this.timeOfWrite = timeOfWrite;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @XmlTransient
    public AmusementPark getAmusementPark() {
        return amusementPark;
    }

    public void setAmusementPark(AmusementPark amusementPark) {
        this.amusementPark = amusementPark;
    }
    
    @XmlTransient
    public Visitor getVisitor() {
        return visitor;
    }

    public void setVisitor(Visitor visitor) {
        this.visitor = visitor;
    }   
}
