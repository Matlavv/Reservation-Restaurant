package Tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.TimeZone;

public class ConnexionBDD
{
    private static Connection cnx;
    public ConnexionBDD() throws ClassNotFoundException, SQLException {
        //String pilote = "com.mysql.jdbc.Driver";
        String pilote = "com.mysql.jdbc.Driver";
        // chargement du pilote
        Class.forName(pilote);
        //Creation de l'objet connection à la BDD
        cnx = DriverManager.getConnection("jdbc:mysql://localhost/reservationrestaurant?useSSL=true&serverTimezone="
                + TimeZone.getDefault().getID(), "root", "");
    }

    //Créer une méthode public pour se connecter à la base de donnée
    public static Connection getCnx() { return cnx; }
}