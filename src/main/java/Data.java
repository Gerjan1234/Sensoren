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
    private static double verwtempgroep1 = 0.0;
    private static double verwtempgroep2 = 0.0;
    private static double verwtempgroep3 = 0.0;
    private static double verwtempgroep4 = 0.0;
    private static double verwtempgroep5 = 0.0;
    private static double verwtempgroep6 = 0.0;
    private static double verwtempgroep7 = 0.0;
    private static double verwtempgroep8 = 0.0;
    private static double verwtempgroep9 = 0.0;
    private static double verwtempretour = 0.0;
    private static double electStand = 0.0;
    private static double gasStand = 0.0;
    private static double electVerrbuik = 0.0;
    private static double gasVerbruik = 0.0;
    private static double lastgasstand = 0.0;
    private static DateFormat SampleTime;
    private static String sampletijd;
    private static Watermeter watermeter;
    private static double watermeterstand = 0.0;


    public static void main(String[] args) { //throws Exception {
        System.out.println("programma gestart");
        Printlog.printlog("programma gestart");
        Data data = new Data();
        sensors = new Sensoren();
        stand = new Meterstanden();
        Templucht = new DHT11();
        dbase = new databaseConnectie();
        watermeter = new Watermeter();
        boolean loopt = true;
        while(loopt = true) {
        data.settime();
        stand.useCCterminal();
        data.getTemperatuur();
        data.getTemperatuurEnLucht();
        data.getMeterstanden();
        data.getLastgasmeterstand();
        data.getwatermeterstand();
        dbase.database(sampletijd,binnentemperatuur, buitentemperatuur, kruipruimtetemperatuur,
            kruipruimteluchtvochtigheid,electStand, gasStand, electVerrbuik, gasVerbruik, verwtempgroep1, verwtempgroep2,verwtempgroep3,
                verwtempgroep4, verwtempgroep5, verwtempgroep6, verwtempgroep7, verwtempgroep8, verwtempgroep9, verwtempretour, watermeterstand);
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
        this.verwtempgroep1 = sensors.getSensoren(2);
        this.verwtempgroep2 = sensors.getSensoren(3);
        this.verwtempgroep3 = sensors.getSensoren(4);
        this.verwtempgroep4 = sensors.getSensoren(5);
        this.verwtempgroep5 = sensors.getSensoren(6);
        this.verwtempgroep6 = sensors.getSensoren(7);
        this.verwtempgroep7 = sensors.getSensoren(8);
        this.verwtempgroep8 = sensors.getSensoren(9);
        this.verwtempgroep9 = sensors.getSensoren(10);
        this.verwtempretour = sensors.getSensoren(11);
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

    public void getwatermeterstand(){
        this.watermeterstand = watermeter.leeswatermeterstand();
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
        System.out.println(verwtempgroep1 + " temperatuur groep 1");
        System.out.println(verwtempgroep2 + " temperatuur groep 2");
        System.out.println(verwtempgroep3 + " temperatuur groep 3");
        System.out.println(verwtempgroep4 + " temperatuur groep 4");
        System.out.println(verwtempgroep5 + " temperatuur groep 5");
        System.out.println(verwtempgroep6 + " temperatuur groep 6");
        System.out.println(verwtempgroep7 + " temperatuur groep 7");
        System.out.println(verwtempgroep8 + " temperatuur groep 8");
        System.out.println(verwtempgroep9 + " temperatuur groep 9");
        System.out.println(verwtempretour + " temperatuur retour");
        System.out.println(watermeterstand + " stand watermeter");

    }

}
