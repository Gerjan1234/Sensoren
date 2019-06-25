import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Printlog {


    protected static void printlog(String logdata) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
            Path p = Paths.get("/home/pi/projectX/logSensoren.txt");
            logdata = System.lineSeparator() + (dateFormat.format(date));
            try {
                Files.write(p, logdata.getBytes(), StandardOpenOption.APPEND);
            } catch (IOException e) {
                System.err.println(e);
            }
        }
}

