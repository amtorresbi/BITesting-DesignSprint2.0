package controllers.transferencia;

import java.io.File;
import java.io.IOException;

public class Tpropias_belapp {

    public static void main(String[] args) throws IOException {
        String ruta = System.getProperty("user.dir") + "\\";
        ProcessBuilder pb = new ProcessBuilder();
        pb.directory(new File(ruta));
        pb.command("cmd", "/c","start", "cmd.exe", "/K", "mvn test -P TPropias-belapp");
        pb.start();
								
    }
    
}
