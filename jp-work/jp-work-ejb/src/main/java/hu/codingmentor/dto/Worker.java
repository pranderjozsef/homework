package hu.codingmentor.dto;

import hu.codingmentor.bean.QueueTopicService;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.inject.Inject;

public class Worker {
    
    private static final Logger LOGGER = Logger.getLogger(Worker.class.getName());
    
    @Inject
    private QueueTopicService queueTopicService;

    public void work(String workerType, double speed){
        while(true){
            Job job = queueTopicService.receiveMessageFromJobQueue();

            if(job == null)
                return;
            else{
                double actualTime = speed * job.getEstimatedTime();

                LOGGER.info("\n--------------------------------------------------\n" 
                        +workerType + " worker just got the job with the id " + job.getId() + 
                        ".\nHe/she needs " + actualTime + " seconds to get the job done. " +
                        "\n--------------------------------------------------");

                try {
                    Thread.sleep((int)(actualTime*1000));
                } catch (InterruptedException ex) {
                    Logger.getLogger(Worker.class.getName()).log(Level.SEVERE, null, ex);
                }

                JobInfo jobInfo = new JobInfo(job.getId(), LocalDateTime.now(), actualTime);
                queueTopicService.sendMessageToJobTopic(jobInfo);
            }
        }
    }
    
    public void makeWorkerWork(){
        //
    }
}
