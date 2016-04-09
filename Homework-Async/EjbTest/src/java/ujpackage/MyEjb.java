package ujpackage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;

@Stateless
public class MyEjb {
    
    @Asynchronous
    public void writeFileElso() {
        try {
            for (int i = 0; i < 100; i++) {
                File myFile = new File("C:/uj/ujfile.txt");
                FileWriter writer = new FileWriter(myFile, true);
                writer.write("Elso\r\n");
                writer.flush();
                writer.close(); 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    @Asynchronous
    public void writeFileMasodik() {
        try {
            for (int i = 0; i < 100; i++) {
                File myFile = new File("C:/uj/ujfile.txt");
                FileWriter writer = new FileWriter(myFile, true);
                writer.write("Masodik\r\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void makeFileEmpty(){
        try {
            File myFile = new File("C:/uj/ujfile.txt");
            FileWriter writer = new FileWriter(myFile);
            writer.write("");
            writer.flush();
            writer.close();    
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
}
