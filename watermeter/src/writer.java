import java.io.FileWriter;


/**
 * class writher
 *
 * @author (Gerjan)
 * @version (179Maart 2020)
 * wegschrijven naar bestand (bestand wordt elke 5 minuten uitgelezen en in database gezet
 */
public class writer {


    public void write(double teller){
        try{
            String telstring = String.valueOf(teller);
            FileWriter fw=new FileWriter("/home/pi/projectX/watermeterstand.ini");
            fw.write(telstring);
            fw.close();
        }catch(Exception e){System.out.println(e);}
        System.out.println("file geupdate met waarde" + teller);
    }
}



