import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

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

    public Double getDHT11(int number) throws Exception {
        //start cu applicatie
        ArrayList<Double> Dht11 = new ArrayList<Double>();
        String command = "python dht22.py";
        Process proc = Runtime.getRuntime().exec(command);
        // Read the output
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = "";
        //size of array uit de meter is 26
        while(Dht11.size()<2) {
            line = reader.readLine();
            Double test = Double.parseDouble(line);
            Dht11.add(test);
        }
        return Dht11.get(number);
    }
}

