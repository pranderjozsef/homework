package hu.codingmentor.bean;

import hu.codingmentor.dto.Job;
import hu.codingmentor.dto.JobInfo;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.Queue;
import javax.jms.Topic;

@Singleton
public class QueueTopicService {
    @Inject
    private JMSContext jMSContext;
 
    @Resource(lookup = "jobKju")
    private Queue jobQueue;
    
    @Resource(lookup = "dzsobTopik")
    private Topic topic;
    
    public void sendMessageToJobQueue(Job job) {
        jMSContext.createProducer().send(jobQueue, job);
    }
 
    public Job receiveMessageFromJobQueue() {
        return  jMSContext.createConsumer(jobQueue).receiveBody(Job.class, 3000);
    }
    
    public void sendMessageToJobTopic(JobInfo jobInfo){
        jMSContext.createProducer().send(topic, jobInfo);
    }
    
    public void makeJobQueueEmpty(){
        JMSConsumer createConsumer = jMSContext.createConsumer(jobQueue);
        while(createConsumer.receive(3000) != null){
            //
        }
    }  
}
