package hu.codingmentor.bean;

import hu.codingmentor.dto.Worker;
import javax.ejb.Stateless;

@Stateless
public class FastWorker extends Worker{

    @Override
    public void makeWorkerWork() {
        new Thread() {
            @Override
            public void run() {
                work("Fast", 0.5);
            }
        }.start();
    }    
}
