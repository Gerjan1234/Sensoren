import java.util.ArrayList;
/**
 * class Sensoren
 *
 * @author Gerjan
 * @version Maart 2019
 * sensoren toegevoegd september 2019
 */
public class Sensoren
{
    //id van sensoren:
    private static String tempbinnen = "28-041592223fff";
    private static String tempbuiten = "28-0415920b12ff";
    private static String verwtempgroep1 ="28-03186bd430ff";
    private static String verwtempgroep2 ="28-03186bf757ff";
    private static String verwtempgroep3 ="28-011876030aff";
    private static String verwtempgroep4 ="28-03186bd3edff";
    private static String verwtempgroep5 ="28-03186bf75bff";
    private static String verwtempgroep6 ="28-03186bd2e6ff";
    private static String verwtempgroep7 ="28-03186bd683ff";
    private static String verwtempgroep8 ="28-03186bd405ff";
    private static String verwtempgroep9 ="28-03186bd3fcff";
    private static String verwtempretour ="28-03186bd583ff";



    private DS1820 Sensorbinnen;
    private DS1820 Sensorbuiten;
    private DS1820 Sensorverwtempgroep1;
    private DS1820 Sensorverwtempgroep2;
    private DS1820 Sensorverwtempgroep3;
    private DS1820 Sensorverwtempgroep4;
    private DS1820 Sensorverwtempgroep5;
    private DS1820 Sensorverwtempgroep6;
    private DS1820 Sensorverwtempgroep7;
    private DS1820 Sensorverwtempgroep8;
    private DS1820 Sensorverwtempgroep9;
    private DS1820 Sensorverwtempretour;

    
    public Sensoren()
    {
        
        Sensorbinnen = new DS1820(tempbinnen);
        Sensorbuiten = new DS1820(tempbuiten);
        Sensorverwtempgroep1 = new DS1820(verwtempgroep1);
        Sensorverwtempgroep2 = new DS1820(verwtempgroep2);
        Sensorverwtempgroep3 = new DS1820(verwtempgroep3);
        Sensorverwtempgroep4 = new DS1820(verwtempgroep4);
        Sensorverwtempgroep5 = new DS1820(verwtempgroep5);
        Sensorverwtempgroep6 = new DS1820(verwtempgroep6);
        Sensorverwtempgroep7 = new DS1820(verwtempgroep7);
        Sensorverwtempgroep8 = new DS1820(verwtempgroep8);
        Sensorverwtempgroep9 = new DS1820(verwtempgroep9);
        Sensorverwtempretour = new DS1820(verwtempretour);

    }

    public Double getSensoren(int number) {
        ArrayList<Double> sensordata = new ArrayList<Double>();
        sensordata.add(Sensorbinnen.readme());
        sensordata.add(Sensorbuiten.readme());
        sensordata.add(Sensorverwtempgroep1.readme());
        sensordata.add(Sensorverwtempgroep2.readme());
        sensordata.add(Sensorverwtempgroep3.readme());
        sensordata.add(Sensorverwtempgroep4.readme());
        sensordata.add(Sensorverwtempgroep5.readme());
        sensordata.add(Sensorverwtempgroep6.readme());
        sensordata.add(Sensorverwtempgroep7.readme());
        sensordata.add(Sensorverwtempgroep8.readme());
        sensordata.add(Sensorverwtempgroep9.readme());
        sensordata.add(Sensorverwtempretour.readme());

        return sensordata.get(number);
    }

}