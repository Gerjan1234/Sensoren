import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * class Meterstanden - geef hier een beschrijving van deze class
 * uitlezen van CU /dev/ttyUSB0 -s 115200
 * Geeft relevante data in retour
 *
 * @author (Gerjan)
 * @version (16 April 2019)
 */

public class Meterstanden
{
    private ArrayList<Double> Meterstanden;
    private HashMap<String, String> MeterstandenMap;
    private String standelect1 = "1-0:1.8.1";
    private String standelect2 = "1-0:1.8.2";
    private String standelecthuidig = "1-0:1.7.0";
    private String standgas1 = "0-1:24.2.1";

    /**
     * Constructor voor objects van class Meterstanden
     * aanmaken van arraylist en hashmap
     */
    public Meterstanden() {   
        Meterstanden = new ArrayList<Double>();
        MeterstandenMap = new HashMap<String, String>();
    }

    /**
     * uitlezen van CCterminal inlezen in en overzetten naar hashmap
     */
    public void useCCterminal() throws Exception {
        ArrayList Meterstandentemp = new ArrayList<Double>();
        //start cu applicatie
        String command = "cu -l /dev/ttyUSB0 -s 115200";
        Process proc = Runtime.getRuntime().exec(command);
        // Read the output
        BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
        String line = "";
        //size of array uit de meter is 26
        while(Meterstandentemp.size()<26) {
            line = reader.readLine();
            Meterstandentemp.add(line);
            if(line.contains(standelect1)){
                MeterstandenMap.put(line.substring(0,9),line.substring(10,line.length()-5));
            }
            else if(line.contains(standelect2)){ 
                MeterstandenMap.put(line.substring(0,9),line.substring(10,line.length()-5));
            }
            else if(line.contains(standelecthuidig)){ 
                MeterstandenMap.put(line.substring(0,9),line.substring(10,line.length()-4));
            }
            else if(line.contains(standgas1)){ 
                MeterstandenMap.put(line.substring(0,10),line.substring(26,line.length()-4));
            }
        }
        System.out.println(MeterstandenMap.size());
        proc.destroy();
        while(MeterstandenMap.size()<4)
        {
            MeterstandenMap.put(null,null);
        }
        System.out.println(MeterstandenMap.size());
        omzettenStanden();
    }

    /**
     * omzetten van data uit de hashmap naar een arraylist zonder voorloopnullen
     */
    public void omzettenStanden() {
        //verwijder 0 
        String standelect1genormaliseerd = MeterstandenMap.get(standelect1);
        if(standelect1genormaliseerd.startsWith("0")) {
            while(standelect1genormaliseerd.startsWith("0")) {
                standelect1genormaliseerd = standelect1genormaliseerd.substring(1,standelect1genormaliseerd.length());
            }
        }
        String standelect2genormaliseerd = MeterstandenMap.get(standelect2);
        if(standelect2genormaliseerd.startsWith("0")) {
            while(standelect2genormaliseerd.startsWith("0")) {
                standelect2genormaliseerd = standelect2genormaliseerd.substring(1,standelect2genormaliseerd.length());
            }
        }
        String standelecthuidiggenormaliseerd = MeterstandenMap.get(standelecthuidig);
        if(standelecthuidiggenormaliseerd.startsWith("0")) {
            while(standelecthuidiggenormaliseerd.startsWith("0")) {
                standelecthuidiggenormaliseerd = standelecthuidiggenormaliseerd.substring(1,standelecthuidiggenormaliseerd.length());
            }
        }
        String standgas1genormaliseerd = MeterstandenMap.get(standgas1);
        if(standgas1genormaliseerd.startsWith("0")) {
            while(standgas1genormaliseerd.startsWith("0")) {
                standgas1genormaliseerd = standgas1genormaliseerd.substring(1,standgas1genormaliseerd.length());
            }
        }
        Double Elect1 = Double.parseDouble(standelect1genormaliseerd);
        Double Elect2 = Double.parseDouble(standelect2genormaliseerd);
        Double ElectHuidig = Double.parseDouble(standelecthuidiggenormaliseerd);
        Double GasStand = Double.parseDouble(standgas1genormaliseerd);
        Double ElectTotaal = Elect1 + Elect2;
        Meterstanden.add(ElectHuidig);
        Meterstanden.add(GasStand);
        Meterstanden.add(ElectTotaal);

    }

    /**
     * doorgeven van de data uit de arraylist
     */
    public Double getMeterStanden(int number) {
        return Meterstanden.get(number);
    }

    /**
     * leegmaken van de arraylist
     */
    public void Leegmaken(){
        Meterstanden.remove(0); 
        Meterstanden.remove(0); 
        Meterstanden.remove(0); 
        MeterstandenMap.remove(standelect1); 
        MeterstandenMap.remove(standelect2); 
        MeterstandenMap.remove(standelecthuidig); 
        MeterstandenMap.remove(standgas1); 
    }

}
