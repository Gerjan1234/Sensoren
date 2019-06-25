import java.util.ArrayList;
/**
 * class Sensoren
 *
 * @author Gerjan
 * @version Maart 2019
 */
public class Sensoren
{
    //id van sensoren:
    private static String tempbinnen = "28-041592223fff";
    private static String tempbuiten = "28-0415920b12ff";
    private DS1820 Sensorbinnen;
    private DS1820 Sensorbuiten;
    
    public Sensoren()
    {
        
        Sensorbinnen = new DS1820(tempbinnen);
        Sensorbuiten = new DS1820(tempbuiten);
    }

    public Double getSensoren(int number) {
        ArrayList<Double> sensordata = new ArrayList<Double>();
        sensordata.add(Sensorbinnen.readme());
        sensordata.add(Sensorbuiten.readme());
        return sensordata.get(number);
    }

}