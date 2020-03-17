import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.io.InputStreamReader;
/**
 * Write a description of class DHT11 here.
 *
 * @author (Gerjan)
 * @version (21 April 2019)
 */
public class DHT11
{
    /**
     * Constructor for objects of class DHT11
     */
    public DHT11(){
    }

    public Double getDHT11(int number)  {
            //start cu applicatie
            ArrayList<Double> Dht11 = new ArrayList<Double>();
            String command = "python dht22.py";
        try {
            Process proc = Runtime.getRuntime().exec(command);
            // Read the output
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            //size of array uit de meter is 26
            while (Dht11.size() < 2) {
                line = reader.readLine();
                try {
                    Double test = Double.parseDouble(line);
                    Dht11.add(test);
                } catch (Exception e) {
                    Printlog.printlog(e.toString());
                    Double test = 0.0;
                    Dht11.add(test);
                }
            }
            }catch (IOException e) {
            Printlog.printlog(e.toString());
        }

        return Dht11.get(number);
    }
}

