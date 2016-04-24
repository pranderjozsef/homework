package hu.codingmentor.dto;

import java.io.Serializable;

public class Job implements Serializable{
    
    private Integer id;
    private Integer estimatedTime;
 
    public Job() {
        //
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(Integer estimatedTime) {
        this.estimatedTime = estimatedTime;
    }
}
