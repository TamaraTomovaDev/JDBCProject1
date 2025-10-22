package secondversion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class InsertStatementExample {
    private static String INSERT_MESSAGES_SQL = "INSERT INTO message" +
            "(id, subject, email) VALUES (?, ?, ?);";

    public static void main(String[] args) throws SQLException {
        InsertStatementExample example = new InsertStatementExample();
        example.insertRecord();
        example.insertMultipleRecordsBatch();
    }

    public void insertRecord() throws SQLException {
        System.out.println(INSERT_MESSAGES_SQL);
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGES_SQL)) {
            preparedStatement.setInt(1, 12);
            preparedStatement.setString(2, "Welcome");
            preparedStatement.setString(3, "welcome@ex.com");
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }

    public void insertMultipleRecordsBatch() {
        System.out.println("Batch starting...");

        List<Object[]> messages = Arrays.asList(
                new Object[]{9, "Reminder", "rem@example.com"},
                new Object[]{10, "Newsletter", "new@example.com"},
                new Object[]{11, "Alert", "alert@example.com"}
        );

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MESSAGES_SQL)) {
            connection.setAutoCommit(false);
            for (Object[] message : messages) {
                preparedStatement.setInt(1, (int) message[0]);
                preparedStatement.setString(2, (String) message[1]);
                preparedStatement.setString(3, (String) message[2]);
                preparedStatement.addBatch();
            }
            int[] result = preparedStatement.executeBatch();
            connection.commit();
            System.out.println("Batch success. Records inserted: " + result.length);
        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
}