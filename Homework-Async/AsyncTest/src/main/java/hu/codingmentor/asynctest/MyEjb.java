package hu.codingmentor.asynctest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Logger;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class MyEjb {
    
    private static final Logger LOGGER = Logger.getLogger(MyEjb.class.getName());
    
    @Asynchronous
    public void writeLogFirst() {
        for (int i = 0; i < 100; i++) {
            LOGGER.info("First");
        }
    }
    
    @Asynchronous
    public void writeLogSecond() {
        for (int i = 0; i < 100; i++) {
            LOGGER.info("Second");
        }
    }
}
