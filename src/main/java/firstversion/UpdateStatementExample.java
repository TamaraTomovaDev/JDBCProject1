package firstversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementExample {
    private static final String UPDATE_USERS_SQL = "UPDATE user SET name = ? WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        UpdateStatementExample updateStatementExample = new UpdateStatementExample();
        updateStatementExample.updateRecord();
    }
    public void updateRecord() throws SQLException {
        try (Connection connection = DriverManager.
                getConnection("jdbc:mysql://localhost:3306/mysql_database?useSSL=false", "intec", "intec-123");
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USERS_SQL)){
                preparedStatement.setString(1, "Ram");
                preparedStatement.setInt(2, 1);
                preparedStatement.executeUpdate();
        }catch (SQLException e) {
            printSQLException(e);
        }
    }
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
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

