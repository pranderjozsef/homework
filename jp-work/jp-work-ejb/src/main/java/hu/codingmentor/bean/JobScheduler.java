package hu.codingmentor.bean;

import hu.codingmentor.dto.Job;
import hu.codingmentor.dto.JobInfo;
import java.time.LocalDateTime;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.ScheduleExpression;
import javax.ejb.Singleton;
import javax.ejb.Timeout;
import javax.ejb.TimerService;
import javax.inject.Inject;

@Singleton
public class JobScheduler {
    private static final Logger LOGGER = Logger.getLogger(JobScheduler.class.getName());
    
    @Inject
    QueueTopicService queueTopicService;
    
    @Inject
    FastWorker fastWorker;
    
    @Inject
    AvarageWorker avarageWorker;
    
    @Inject
    SlowWorker slowWorker;
    
    @Resource
    TimerService timerService;
    
    private int id = 0;
    
    public void startScheduler(){
        queueTopicService.makeJobQueueEmpty();
        
        timerService.createCalendarTimer(new ScheduleExpression()
                .hour("*").minute("*").second("*/30"));
    }
    
    @Timeout
    public void createTenJobs(){
        Job job;
        for (int i = 0; i < 5; i++) {
            job = new Job();
            
            job.setId(id++);
            job.setEstimatedTime((int)(Math.random() * 5) + 1);
            
            LOGGER.info("Id:" + job.getId() + ", Estimated time: " + job.getEstimatedTime());
            
            queueTopicService.sendMessageToJobQueue(job);
            
            JobInfo jobInfo = new JobInfo(job.getId(), LocalDateTime.now(), null);

            queueTopicService.sendMessageToJobTopic(jobInfo);
        }
        
        fastWorker.makeWorkerWork();
        avarageWorker.makeWorkerWork();
        slowWorker.makeWorkerWork();
    }
}
