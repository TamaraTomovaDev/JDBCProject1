package firstversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatementExample {
    private static final String createTableSQL = "create table user (\n" + " id int(3) primary key, \n" +
            " name varchar(20),\n" + " email varchar(20),\n" + " country varchar(20),\n" +
            " password varchar(20)\n" + " );";

    public static void main(String[] args) throws SQLException {
        CreateStatementExample createTableExample = new CreateStatementExample();
        createTableExample.createTable();
    }
    public void createTable() throws SQLException {
        System.out.println((createTableSQL));
        // Stap 1: een connectie maken door de DB(SSL=Secure Sockets Layer)
        try(Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "intec", "intec-123");
            // Stap 2: maak een verklaring aan met behulp van het Connection-object
            Statement statement = connection.createStatement();){
            // Stap 3: de query of update qeury uotvoeren
            statement.execute(createTableSQL);
        }catch (SQLException e){
            // druk sql exaption informatie af
            printSQLException(e);
        }
        // Stap 4: try-with-resource statement zal de verbinding automatisch aluiten
    }
    public static void printSQLException(SQLException ex){
        for (Throwable e:ex){
            if(e instanceof SQLException){
                e.printStackTrace(System.err);
                System.err.println("SQLState: " +((SQLException)e).getSQLState());
                System.err.println("Error Code: " +((SQLException)e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = e.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
