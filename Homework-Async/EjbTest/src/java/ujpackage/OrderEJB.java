package ujpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.Future;
import javax.annotation.Resource;
import javax.ejb.AsyncResult;
import javax.ejb.Asynchronous;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;


@Stateless
@Asynchronous
public class OrderEJB {
 
    @Resource
    SessionContext ctx;
 
    public Future<Boolean> sendOrderToWorkflow() {
        try {
            File myFile = new File("C:/uj/ujfile.txt");
            
            for (int i = 0; i < 400; i++) {
                FileWriter writer = new FileWriter(myFile, true);
                if(ctx.wasCancelCalled()) {
                    writer.write(i + "\r\n");
                    writer.flush();
                    writer.close();
                    return new AsyncResult<>(true);
                }

                writer.write(i + "\r\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return new AsyncResult<>(false);
    }
}
