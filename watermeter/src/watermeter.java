
import com.pi4j.io.gpio.*;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

/**
 * class Watermeter
 *
 * @author (Gerjan)
 * @version (17 Maart 2020)
 * uilezen van inductive sensor
 */
public class watermeter {

    public static long counter;
    public static long kuub;
    public static long kuubteller;
    public static double teller;


    public static void main(String args[])throws InterruptedException {
counter = 302928;
kuub = 302;
kuubteller = 0;
teller = 302.928;

        // create gpio controller
        final GpioController gpio = GpioFactory.getInstance();

        // provision gpio pin #07 as an input pin with its internal pull down resistor enabled
        final GpioPinDigitalInput myButton = gpio.provisionDigitalInputPin(RaspiPin.GPIO_02, PinPullResistance.PULL_DOWN);
writer x = new writer();
        // set shutdown state for this input pin
        //myButton.setShutdownOptions(true);

        // create and register gpio pin listener
        myButton.addListener(new GpioPinListenerDigital() {
            @Override
            public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {
                // display pin state on console
                System.out.println(" --> GPIO PIN STATE CHANGE: " + event.getPin() + " = " + event.getState());
                if(event.getState().isHigh()){
                    counter++;
                    kuubteller++;
                    System.out.println("totaal teller = " + counter);
                    System.out.println("totaal kuubs = " + kuubteller);
                    System.out.println("totaal huidige kuub = " + kuub + " liter");
                    teller++;
                    x.write(teller);
                    if(kuubteller == 1000){
                        kuubteller = 0;
                        kuub++;
                    }
                }
            }

        });

        System.out.println("Start uitlezen");

        // keep program running until user aborts (CTRL-C)
        while(true) {
            Thread.sleep(500);
        }

        // stop all GPIO activity/threads by shutting down the GPIO controller
        // (this method will forcefully shutdown all GPIO monitoring threads and scheduled tasks)
        // gpio.shutdown();   <--- implement this method call if you wish to terminate the Pi4J GPIO controller
    }


    }


