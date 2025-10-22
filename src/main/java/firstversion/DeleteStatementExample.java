package firstversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStatementExample {
    private static final String DELETE_USERS_SQL = "DELETE FROM user WHERE id = 3";
    public static void main(String[] args) throws SQLException {
        DeleteStatementExample deleteStatementExample = new DeleteStatementExample();
        deleteStatementExample.deleteRecord();
    }
    public void deleteRecord() throws SQLException {
        System.out.println(DELETE_USERS_SQL);
        try (Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "intec", "intec-123");
             Statement statement = connection.createStatement();){
            int result = statement.executeUpdate(DELETE_USERS_SQL);
            System.out.println( "Number of records affected :: "+ result);
        }catch (SQLException e) {
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
