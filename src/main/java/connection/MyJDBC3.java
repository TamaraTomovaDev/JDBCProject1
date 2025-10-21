package connection;

import java.sql.*;

public class MyJDBC3 {
    public static void main(String[] args) {
        try{
            // Stap 01 : een verbiding openen
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/thebelgianbrewerydb", "intec", "intec-123");
            System.out.println("Step 2 -> SUCCESS: " + "Connection is made between MYSQL and JAVA");

            // SStap 02: een / meerdere queries uitvoeren | statement uitvoeren

            Statement selectStatement = connection.createStatement();
            String selectQuery = "select Id, Name, BrewerId, CategoryId, Price, Alcohol from beers";
            ResultSet selectResult = selectStatement.executeQuery(selectQuery);
            while (selectResult.next()){
                System.out.println(
                        selectResult.getRow() + " | " +
                                selectResult.getInt("Id") + " | " +
                                selectResult.getString("Name") + " | " +
                                selectResult.getInt("BrewerId") + " | " +
                                selectResult.getInt("CategoryId") + " | " +
                                selectResult.getFloat("Price") + " | " +
                                selectResult.getInt("Stock") + " | " +
                                selectResult.getFloat("Alcohol")
                );
            }
        }catch (SQLException sqlException){
            System.err.println(("Step 2 -> SQL EXCEPTION: " + sqlException.getMessage()));
        }
    }
}
