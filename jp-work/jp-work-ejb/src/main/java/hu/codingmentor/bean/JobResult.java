package hu.codingmentor.bean;

import hu.codingmentor.dto.JobInfo;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.Singleton;

@Singleton
public class JobResult {
    private Map<Integer,String> results = new HashMap<>();
    
    public void saveResult(JobInfo jobInfo){
        if(jobInfo.getProcessingTime() < 5)
            results.put(jobInfo.getJobId(), "Success");
        else
            results.put(jobInfo.getJobId(), "Failure");
    }

    public Map<Integer, String> getResults() {
        return results;
    }   
}
