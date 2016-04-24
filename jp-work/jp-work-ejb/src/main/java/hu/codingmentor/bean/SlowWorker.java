package hu.codingmentor.bean;

import hu.codingmentor.dto.Worker;
import javax.ejb.Stateless;

@Stateless
public class SlowWorker extends Worker{
    
    @Override
    public void makeWorkerWork() {
        new Thread() {
            @Override
            public void run() {
                work("Slow", 2);
            }
        }.start();
    }    
}
