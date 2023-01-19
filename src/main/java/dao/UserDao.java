package dao;

import model.User;
import java.sql.*;

public class UserDao {
    public int registerUser(User user) throws ClassNotFoundException {
        String INSERT_USERS_SQL = "INSERT INTO user" +
                "  (username, password) VALUES " +
                " (?, ?);";

        int result = 0;

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/boxchatjavaee?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            result = preparedStatement.executeUpdate();

        } catch (SQLException e) {
            // process sql exception
            printSQLException(e);
        }
        return result;
    }

    public boolean confirmUser(User user) throws ClassNotFoundException {
        String sql = "SELECT username, password  FROM user WHERE username = ?";

        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection connection = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/boxchatjavaee?useSSL=false", "root", "root");

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUsername());

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet result =  preparedStatement.executeQuery();

            User querriedUser = null;

            if (result.next()) {
                querriedUser = new User(result.getString("username"), result.getString("password"));
            }

            connection.close();

            if (querriedUser != null) {
                if (querriedUser.getUsername().equals(user.getUsername())  && querriedUser.getPassword().equals(user.getPassword())) {
                    return true;
                }
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return false;
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
