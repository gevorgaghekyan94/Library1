package src.services;

import org.mindrot.jbcrypt.BCrypt;

import java.sql.*;

import static src.constants.MariaDBConstants.*;

public class LibraryService {

    private static LibraryService instance = null;

    public static LibraryService getInstance() {
        if (instance == null) {
            instance = new LibraryService();
        }
        return instance;
    }

    public boolean checkPassword(int customerId, String password) {
        boolean check = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String customerPassword = null;
                String query = "SELECT password FROM Customers WHERE id = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, customerId);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    customerPassword = resultSet.getString("password");
                }

                if (BCrypt.checkpw(password, customerPassword)) {
                    check = BCrypt.checkpw(password, customerPassword);
                }
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
        return check;
    }

    public void TakeBookFromLibrary(int customerId, int bookId) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                String state = null;

                String query = "SELECT state FROM Books " +
                        "WHERE state = 'NOT_TAKEN';";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    state = resultSet.getString("state");
                }

                if (state.equals("NOT_TAKEN")) {

                    String query1 = "INSERT INTO Orders(returnDate, customerId,bookId) " +
                            "VALUES (DATE_ADD(now(),INTERVAL 15 DAY ),?,?);";
                    String query2 = "INSERT INTO History(customerId,OrderId) " +
                            "VALUES (?,?);";
                    String query3 = "UPDATE Books\n" +
                            "SET state = 'TAKEN'\n" +
                            "WHERE id = ?;";

                    PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
                    preparedStatement1.setInt(1, customerId);
                    preparedStatement1.setInt(2, bookId);

                    PreparedStatement preparedStatement2 = conn.prepareStatement(query2);
                    preparedStatement2.setInt(1, customerId);
                    preparedStatement2.setInt(2, bookId);

                    PreparedStatement preparedStatement3 = conn.prepareStatement(query3);
                    preparedStatement3.setInt(1, bookId);

                    preparedStatement1.executeUpdate();
                    preparedStatement2.executeUpdate();
                    preparedStatement3.executeUpdate();
                }
            }
        } catch (SQLIntegrityConstraintViolationException ex) {
            System.out.println("The book is taken or you have already taken the book");
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }

    public void returnBookToLibrary(int customerId, int bookId) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "DELETE FROM Orders WHERE customerId = ?";
                String query1 = "UPDATE Books SET state = 'NOT_TAKEN' WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, customerId);

                PreparedStatement preparedStatement1 = conn.prepareStatement(query1);
                preparedStatement1.setInt(1, bookId);

                preparedStatement.executeUpdate();
                preparedStatement1.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }
}
