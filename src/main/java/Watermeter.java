import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;



/**
 * class Watermeter
 *
 * @author (Gerjan)
 * @version (17 Maart 2020) pom geupdate
 * uilezen van tekst file met stand watermeter wat gevuld wordt door watermeter.jar
 */
public class Watermeter {

    private Double stand;


    public Double leeswatermeterstand() {
    String x = "000.000";
        Path fileLocation = Paths.get("/home/pi/projectX/", "watermeterstand.ini");
        Charset charset = Charset.forName("ISO-8859-1");
        try {
            List<String> lines = Files.readAllLines(fileLocation, charset);
            for (String line : lines) {
                x = line;
            }
        } catch (IOException e) {
            Printlog.printlog(e.toString());
        }
        stand = Double.parseDouble(x);
        return stand;

}
}