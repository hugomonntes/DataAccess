package data_access.T1EX;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class AD_EX11 {
    // 11. Compara la eficiencia de copiar un fichero de 100MB usando por un lado
    // BufferedInputStream y BufferedOutputStream y por otro FileInputStream y
    // FileOutputStream. En este último caso prueba con los siguientes tamaños de
    // buffer: 10,100,1000, …

    public static void Buffered() {
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Hola");
            }
            
        }, 10);
        try (FileInputStream fis = new FileInputStream(new File("prueba100MB.txt"));
                BufferedInputStream bis = new BufferedInputStream(fis)) {
                    bis.read();
        } catch (Exception e) {
            // TODO: handle exception
        }
    }

    public static void main(String[] args) {
        long principio = new Date().getTime();
        try (FileInputStream fis = new FileInputStream(new File("prueba100MB.txt"));
                BufferedInputStream bis = new BufferedInputStream(fis)) {
                    bis.read();
        } catch (Exception e) {
        }
        long finaal = new Date().getTime();
        System.out.println(finaal - principio + "ms");
    }
}
