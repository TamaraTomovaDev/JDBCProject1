package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyJDBC2 {
    public static void main(String[] args) {
        // Stap 1: Probeer de MySQL JDBC-driver te laden
        try {
            // Let op: deze driverklasse is verouderd. Gebruik liever "com.mysql.cj.jdbc.Driver"
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("SUCCESS: Driver is geconfigureerd");
        } catch (ClassNotFoundException notFoundException) {
            System.out.println("CLASS NOT FOUND: " + notFoundException.getMessage());
        }

        Connection connection = null;

        // Stap 2: Maak verbinding met de MySQL-database met URL naar database, gebruikersnaam en wachtwoord
        try {
            connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb", "intec", "intec-123"
            );
            System.out.println("SUCCESS: Verbinding is gemaakt tussen MySQL en Java");
        } catch (SQLException sqlException) {
            System.err.println("SQL Exception: " + sqlException.getMessage());
        }

        // Stap 3: Als de verbinding gelukt is, toon metadata over de database
        if (connection != null) {
            System.out.println("Mijn statements komen hier ...");
            try {
                // Toon informatie over de verbinding
                System.out.println("Verbonden met: " + connection.getMetaData().getURL());
                System.out.println("Database productnaam: " + connection.getMetaData().getDatabaseProductName());
                System.out.println("Database versie: " + connection.getMetaData().getDatabaseProductVersion());
                System.out.println("Verbonden gebruiker: " + connection.getMetaData().getUserName());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}