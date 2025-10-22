package secondversion;

import java.sql.*;

public class SelectStatementExample {
    public static final String QUERY = "select id, subject, email from message where id = ?";

    public static void main(String[] args) {
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(QUERY);){
            preparedStatement.setInt(1,9);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                String subject = rs.getString("subject");
                String email = rs.getString("email");
                System.out.println(id + ", " + subject + ", " + email);
            }
        }  catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }
    }
}
