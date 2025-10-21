package connection;

import java.sql.*;

public class MyJDBC1 {
    public static void main(String[] args) {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/joinsdb", "intec", "intec-123");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from users");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("user_name"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
