import java.sql.*;

/**
 * Write a description of class databaseConnectie here.
 *
 * @author (Gerjan Kruize)
 * @version (23 April 2019)
 */
public class databaseConnectie {
    private static final String JDBC_URL = "jdbc:mysql://localhost/hr?serverTimezone=EST";
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
            kruipruimteluchtvochtigheid, double electStand, double gasStand, double electVerrbuik, double gasVerbruik) {
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
            rs.insertRow();
            rs.beforeFirst();
            con.close();
            System.out.println("update database oke");
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
