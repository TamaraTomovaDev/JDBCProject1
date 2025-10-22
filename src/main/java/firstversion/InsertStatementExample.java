package firstversion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class InsertStatementExample {
    private static String INSERT_USERS_SQL = "INSERT INTO user" +
            "(id, name, email, country, password) VALUES (?, ?, ?, ?, ?);";

    public static void main(String[] args)  throws SQLException {
        InsertStatementExample createTableExample = new InsertStatementExample();
        createTableExample.insertRecord();
        createTableExample.insertMultipleRecordsBatch();
    }
    public void insertRecord() throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // Stap 1: een verbiding naar de DB
        try (Connection connection = DriverManager.
            getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "intec", "intec-123");
            // Stap 2: Maak een verklaring aan met behulp van het verbidingsobject
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)){
            preparedStatement.setInt(1, 1);
            preparedStatement.setString(2, "John");
            preparedStatement.setString(3,"john@doe.com");
            preparedStatement.setString(4,"BE");
            preparedStatement.setString(5,"secretpass");
            System.out.println(preparedStatement);
            // Stap 3: het uitvoeren van de update-query
            preparedStatement.executeUpdate();
        } catch (SQLException e){
            // druk sql-uitzonderingsinformatie af
            printSQLException(e);
        }
        // Spap 4: try-with-recource statement zal de verbinding automatisch sluiten
    }
        public void insertMultipleRecordsBatch() {
            System.out.println("Batch starting...");

            List<Object[]> users = Arrays.asList(
                    new Object[]{2, "Alice", "alice@example.com", "NL", "alicepass"},
                    new Object[]{3, "Bob", "bob@example.com", "DE", "bobpass"},
                    new Object[]{4, "Charlie", "charlie@example.com", "TR", "charliepass"}
            );

            try (Connection connection = DriverManager.
                    getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "intec", "intec-123");
                 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
                connection.setAutoCommit(false);
                for (Object[] user : users) {
                    preparedStatement.setInt(1, (int) user[0]);
                    preparedStatement.setString(2, (String) user[1]);
                    preparedStatement.setString(3, (String) user[2]);
                    preparedStatement.setString(4, (String) user[3]);
                    preparedStatement.setString(5, (String) user[4]);
                    preparedStatement.addBatch();
                }
                int[] result = preparedStatement.executeBatch();
                connection.commit();
                System.out.println("Batch succec.Result: " + result.length);
            } catch (SQLException e) {
                printSQLException(e);
            }
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
