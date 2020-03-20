import java.sql.*;

/**
 * Write a description of class databaseConnectie here.
 *
 * @author (Gerjan Kruize)
 * @version (23 April 2019)
 */
public class databaseConnectie {
    private static final String JDBC_URL = "jdbc:mysql://localhost/hr?serverTimezone=UTC";
    private static String connectie;
    private static String user;
    private static String password;
    private static String database;
    private static String tabel;
    private RepoUitlezen uitlezen;

    /**
     * Constructor for objects of class databaseConnectie
     * data ophalen uit repofile
     */

    public databaseConnectie() {
        uitlezen = new RepoUitlezen();
        this.user = uitlezen.getRepodata(0);
        this.password = uitlezen.getRepodata(1);
        this.connectie = uitlezen.getRepodata(2);
        this.database = uitlezen.getRepodata(3);
        this.tabel = uitlezen.getRepodata(4);
    }

    /**
     * deze methode maak een connectie met de datbase
     *
     * @param input van uit class data
     */

    public static void database(String sampletijd, double binnentemperatuur, double buitentemperatuur, double kruipruimtetemperatuur, double
            kruipruimteluchtvochtigheid, double electStand, double gasStand, double electVerrbuik, double gasVerbruik, double verwtempgroep1,
            double verwtempgroep2, double verwtempgroep3, double verwtempgroep4, double verwtempgroep5, double verwtempgroep6,
            double verwtempgroep7, double verwtempgroep8, double verwtempgroep9, double verwtempretour, double watermeterstand) {
        try {
            //Class.forName("com.mysql.jdbc.Driver");
            //Connection con = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s", connectie, database), user, password);
            Connection con = DriverManager.getConnection(String.format("jdbc:mysql://%s/%s?serverTimezone=EST", connectie, database), user, password);
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("Select * From " + tabel);
            rs.moveToInsertRow();
            rs.updateString("Timestamp", sampletijd);
            rs.updateDouble("temperatuur_binnen", binnentemperatuur);
            rs.updateDouble("Temperatuur_buiten", buitentemperatuur);
            rs.updateDouble("Temperatuur_kruipruimte", kruipruimtetemperatuur);
            rs.updateDouble("Luchtvochtigheid_kruipruimpte", kruipruimteluchtvochtigheid);
            rs.updateDouble("Huidigelectverbruik", electStand);
            rs.updateDouble("Totaalstandgasmeter", gasStand);
            rs.updateDouble("Totaalstandelectmeter", electVerrbuik);
            rs.updateDouble("gasVerrbuik", gasVerbruik);
            rs.updateDouble("verwtempgroep1", verwtempgroep1);
            rs.updateDouble("verwtempgroep2", verwtempgroep2);
            rs.updateDouble("verwtempgroep3", verwtempgroep3);
            rs.updateDouble("verwtempgroep4", verwtempgroep4);
            rs.updateDouble("verwtempgroep5", verwtempgroep5);
            rs.updateDouble("verwtempgroep6", verwtempgroep6);
            rs.updateDouble("verwtempgroep7", verwtempgroep7);
            rs.updateDouble("verwtempgroep8", verwtempgroep8);
            rs.updateDouble("verwtempgroep9", verwtempgroep9);
            rs.updateDouble("verwtempretour", verwtempretour);
            rs.updateDouble("watermeterstand", watermeterstand);
            rs.insertRow();
            rs.beforeFirst();
            con.close();
            System.out.println("update database oke");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
