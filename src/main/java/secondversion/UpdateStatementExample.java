package secondversion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStatementExample {
    private static final String UPDATE_Message_SQL = "UPDATE message SET subject = ? WHERE id = ?";

    public static void main(String[] args) throws SQLException {
        secondversion.UpdateStatementExample updateStatementExample = new secondversion.UpdateStatementExample();
        updateStatementExample.updateRecord();
    }
    public void updateRecord() throws SQLException {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_Message_SQL)){
            preparedStatement.setString(2, "Ram");
            preparedStatement.setInt(2, 1);
            preparedStatement.executeUpdate();
        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
}


