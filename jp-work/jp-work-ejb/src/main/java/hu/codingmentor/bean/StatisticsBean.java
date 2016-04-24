package hu.codingmentor.bean;

import hu.codingmentor.dto.JobInfo;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;

@MessageDriven(mappedName = "dzsobTopik")
public class StatisticsBean implements MessageListener{

    private static final Logger LOGGER = Logger.getLogger(StatisticsBean.class.getName());
    
    @Inject
    JobResult jobResult;
    
    @Override
    public void onMessage(Message message) {
        try {
            JobInfo jobInfo = message.getBody(JobInfo.class);
            
            if(jobInfo.getProcessingTime() == null){
                LOGGER.info("\n==================================================\n" +
                        "New job has become available.\n" +
                        "Id: " + jobInfo.getJobId() +
                        "\nCreation date: " + jobInfo.getDateTime() +
                        "\n==================================================");
            }
            else{
                jobResult.saveResult(jobInfo);
            }
        } catch (JMSException ex) {
            Logger.getLogger(StatisticsBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
