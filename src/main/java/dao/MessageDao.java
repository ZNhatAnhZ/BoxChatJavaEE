package dao;

import model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MessageDao {
    public int registerMessage(Message message) throws ClassNotFoundException {
        String sql = "INSERT INTO messages" +
                " (user_id, content) VALUES " +
                "((select id from user where username = ?), ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/boxchatjavaee?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, message.getUsername());
            preparedStatement.setString(2, message.getContent());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    public List<Message> getAllMessages() throws ClassNotFoundException {
        List<Message> result = new ArrayList<Message>();

        String sql = "select user.username, messages.content from messages" +
                " join user on user.id = messages.user_id order by messages.id asc;";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/boxchatjavaee?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            System.out.println(preparedStatement);


            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()){
                result.add(new Message(rs.getString("username"), rs.getString("content")));
            }

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
