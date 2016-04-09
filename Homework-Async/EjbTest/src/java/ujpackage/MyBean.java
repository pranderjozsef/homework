package ujpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;


@Named
@RequestScoped
public class MyBean {
    
    @EJB
    MyEjb myEjb;
    
    @EJB
    OrderEJB orderEJB;

    public MyBean() {
    }
    
    public void writeFiles() throws IOException{
        myEjb.makeFileEmpty();
        myEjb.writeFileElso();
        myEjb.writeFileMasodik();
    }
    
    public void writeStatus(int millis) throws InterruptedException, ExecutionException{
        myEjb.makeFileEmpty();
        try {
            Future<Boolean> future = orderEJB.sendOrderToWorkflow();
            Thread.sleep(millis);
            future.cancel(true);
            
            File myFile = new File("C:/uj/ujfile.txt");
            FileWriter writer = new FileWriter(myFile, true);
            writer.write("Has client interrupted the task before its completing: " + future.get().toString() + "\r\n");
            writer.flush();
            writer.close();    
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}
