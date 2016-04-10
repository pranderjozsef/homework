package hu.codingmentor.asynctest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Future;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;


@Stateless
@Asynchronous
public class OrderEJB {
 
    @Resource
    private SessionContext ctx;
    
    private static final Logger LOGGER = Logger.getLogger(OrderEJB.class.getName());
    
    public Future<Boolean> sendOrderToWorkflow() {
        for (int i = 0; i < 400; i++) {
            if(ctx.wasCancelCalled()) {
                LOGGER.log(Level.INFO, "{0}", i);
                return new AsyncResult<>(true);
            }
            LOGGER.log(Level.INFO, "{0}", i);
        }
        return new AsyncResult<>(false);
    }
}
