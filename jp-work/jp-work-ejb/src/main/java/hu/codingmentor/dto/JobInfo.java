package hu.codingmentor.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

public class JobInfo implements Serializable{
    private Integer jobId;
    private LocalDateTime dateTime;
    private Double processingTime;

    public JobInfo() {
        //
    }

    public JobInfo(Integer jobId, LocalDateTime dateTime, Double processingTime) {
        this.jobId = jobId;
        this.dateTime = dateTime;
        this.processingTime = processingTime;
    }
    
    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Double getProcessingTime() {
        return processingTime;
    }

    public void setProcessingTime(Double processingTime) {
        this.processingTime = processingTime;
    }
}
