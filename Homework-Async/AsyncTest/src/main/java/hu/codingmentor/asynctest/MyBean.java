package hu.codingmentor.asynctest;

//import hu.codingmentor.asynctest.OrderEJB;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;


@Named
@RequestScoped
public class MyBean {
    
    private static final Logger LOGGER = Logger.getLogger(MyBean.class.getName());
    
    @Inject
    private MyEjb myEjb;
    
    @Inject
    private OrderEJB orderEJB;

    public MyBean() {
    }
    
    public void writeLogVoid() throws IOException{
        LOGGER.info("==================Void Asyncron==================");
        myEjb.writeLogFirst();
        myEjb.writeLogSecond();
    }
    
    public void writeLogFuture(int millis) throws InterruptedException, ExecutionException{
        LOGGER.info("==================Future Asyncron==================");
        Future<Boolean> future = orderEJB.sendOrderToWorkflow();
        Thread.sleep(millis);
        future.cancel(true);
        LOGGER.log(Level.INFO, "Has client interrupted the task before its completing: {0}", 
                future.get().toString());
    }
}
