import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Class DS1820
 *
 * @author Gerjan
 * @version Maart 2019
 */
public class DS1820 {

    //path to search for devices in filesystem
    private String devicesPath = "/sys/bus/w1/devices";
    // file of the measured values
    private String valueFile = "w1_slave";
    private String Sensor;
    private Double temp = 9999.99;

    /**
     * Constructor voor objects van class DS1820
     * aanmaken sensor
     */
    public DS1820(String Sensor)
    {
        this.Sensor = Sensor;
    }

    /**
     * uitlezen per sensor
     */
    
    public Double readme()
    {

        Path path = FileSystems.getDefault().getPath(devicesPath, Sensor, valueFile);
        List<String> lines;
        boolean crcOK = false;
            try {
                lines = Files.readAllLines(path);
                for(String line: lines) {
                    if (line.endsWith("YES"))
                        crcOK = true;
                    else if (line.matches(".*t=[0-9-]+") && crcOK) 
                        this.temp = Integer.valueOf(line.substring(line.indexOf("=")+1))/1000.0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        return temp;
    }
}