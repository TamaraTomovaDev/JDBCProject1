package secondversion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DeleteStatementExample {
    private static final String DELETE_Messages_SQL = "DELETE FROM message WHERE id = 10";
    public static void main(String[] args) throws SQLException {
        secondversion.DeleteStatementExample deleteStatementExample = new secondversion.DeleteStatementExample();
        deleteStatementExample.deleteRecord();
    }
    public void deleteRecord() throws SQLException {
        System.out.println(DELETE_Messages_SQL);
        try (Connection connection = JDBCUtils.getConnection();
                Statement statement = connection.createStatement();){
            int result = statement.executeUpdate(DELETE_Messages_SQL);
            System.out.println( "Number of records affected :: "+ result);
        }catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
}

