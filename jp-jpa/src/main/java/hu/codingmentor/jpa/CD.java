package hu.codingmentor.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;

@Entity
@NamedQuery(name = "findCDByRecordLabel",
        query = "SELECT c FROM CD c WHERE c.recordLabel = :rl")
public class CD extends Item{
    
    @Column(name = "RECORD_LABEL")
    private String recordLabel;
    
    @Column(name = "NUMBER_OF_TRACKS")
    private Integer numberOfTracks;

    public CD() {
        //Empty
    }

    public String getRecordLabel() {
        return recordLabel;
    }

    public void setRecordLabel(String recordLabel) {
        this.recordLabel = recordLabel;
    }

    public Integer getNumberOfTracks() {
        return numberOfTracks;
    }

    public void setNumberOfTracks(Integer numberOfTracks) {
        this.numberOfTracks = numberOfTracks;
    }

    @Override
    public String toString() {
        return super.toString() + ", recordLabel=" + recordLabel + ", numberOfTracks=" + numberOfTracks + '}';
    }   
}
