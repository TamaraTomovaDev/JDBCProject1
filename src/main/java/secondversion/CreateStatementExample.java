package secondversion;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateStatementExample {
    private static final String createTableSQL = "create table message (\n" + "id int(3) primary key,\n" +
            " subject varchar(20), \n" + " email varchar(20) \n" + " );";
    public static void main(String[] args) throws SQLException {
        CreateStatementExample example = new CreateStatementExample();
        example.createTable();
    }
    public void createTable() throws SQLException {
        System.out.println(createTableSQL);
        try(Connection connection = JDBCUtils.getConnection();
            Statement statement = connection.createStatement();){
            statement.executeUpdate(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
            // druk SQL exception information af
            JDBCUtils.printSQLException(e);
        }
    }
}

