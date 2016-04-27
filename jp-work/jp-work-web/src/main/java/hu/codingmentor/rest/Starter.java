package hu.codingmentor.rest;

import hu.codingmentor.bean.JobResult;
import hu.codingmentor.bean.JobScheduler;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/")
public class Starter {
    
    @Inject
    private JobScheduler jobScheduler;
    
    @Inject
    private JobResult jobResult;
    
    @POST
    public Boolean startScheduler() {
        jobScheduler.startScheduler();
        return true;
    }
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getResult(){
        return jobResult.getResults().toString();
    }
}
