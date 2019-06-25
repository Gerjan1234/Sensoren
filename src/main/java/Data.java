import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.DateFormat;
import java.util.Scanner;
/**
 * A class for creating log files of random data.
 *
 * @author Gerjan
 * @version Maart 2019
 */
public class Data
{

    private static Sensoren sensors;
    private static Meterstanden stand;
    private static DHT11 Templucht;
    private static databaseConnectie dbase;
    private static double binnentemperatuur = 0.0;
    private static double buitentemperatuur = 0.0;
    private static double kruipruimtetemperatuur = 0.0;
    private static double kruipruimteluchtvochtigheid = 0.0;
    private static double electStand = 0.0;
    private static double gasStand = 0.0;
    private static double electVerrbuik = 0.0;
    private static double gasVerbruik = 0.0;
    private static double lastgasstand = 0.0;
    private static DateFormat SampleTime;
    private static String sampletijd;

    public static void main(String[] args) { //throws Exception {
        System.out.println("programma gestart");
        Printlog.printlog("programma gestart");
        Data data = new Data();
        sensors = new Sensoren();
        stand = new Meterstanden();
        Templucht = new DHT11();
        dbase = new databaseConnectie();
        boolean loopt = true;
        while(loopt = true) {
        data.settime();
        stand.useCCterminal();
        data.getTemperatuur();
        data.getTemperatuurEnLucht();
        data.getMeterstanden();
        data.getLastgasmeterstand();
        dbase.database(sampletijd,binnentemperatuur, buitentemperatuur, kruipruimtetemperatuur,
            kruipruimteluchtvochtigheid,electStand, gasStand, electVerrbuik, gasVerbruik);
        data.getHuidiggasverbruik();
        stand.Leegmaken();
        data.printOut();
        try {
        TimeUnit.SECONDS.sleep(300);
        } catch (InterruptedException e) {
            Printlog.printlog(e.toString());
        }
    }
    }

    public void getTemperatuur() {
        this.binnentemperatuur = sensors.getSensoren(0);
        this.buitentemperatuur = sensors.getSensoren(1);
    }

    public void getTemperatuurEnLucht() {
        this.kruipruimtetemperatuur = Templucht.getDHT11(0);
        this.kruipruimteluchtvochtigheid = Templucht.getDHT11(1);

    }
    public void getLastgasmeterstand() {
        this.lastgasstand = gasStand;
    }

    public void getHuidiggasverbruik() {
        double verbruik = gasStand - lastgasstand;
        if (verbruik > 100){
            this.gasVerbruik = 0;
        } else {
        this.gasVerbruik = gasStand - lastgasstand;
        }
    }



    public void getMeterstanden() {
        this.electStand = stand.getMeterStanden(0);
        this.gasStand = stand.getMeterStanden(1);
        this.electVerrbuik = stand.getMeterStanden(2);
        this.gasVerbruik = gasStand - lastgasstand;
    }

    public void settime() {
        SampleTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        SampleTime.setTimeZone(TimeZone.getTimeZone("Europe/Amsterdam"));
        Date date = new Date();
        this.sampletijd = SampleTime.format(date);
    }

    public void printOut(){
        System.out.println(sampletijd);
        System.out.println(binnentemperatuur + " temperatuur binnen");
        System.out.println(buitentemperatuur + " temperatuur buiten");
        System.out.println(kruipruimtetemperatuur  + " temperatuur kruipruimte");
        System.out.println(kruipruimteluchtvochtigheid  + " luchtvochtigheid kruipruimte");
        System.out.println(electStand  + " huidig elect verbuik");
        System.out.println(gasStand + " totaalstand gasmeter");
        System.out.println(electVerrbuik + " totaalstand electmeter");
        System.out.println(gasVerbruik + " huidig gas verbuik");
    }

}
