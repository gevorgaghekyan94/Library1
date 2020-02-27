package src;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static src.constants.MariaDBConstants.*;

public class CreateDatabaseAndTables {

    public static void createDatabaseAndTables() {
        createDatabase();
        createTableBooks();
        createTableAuthors();
        createTableAuthors_Books();
        createTableCustomers();
        createTableOrders();
        createTableHistory();
    }

    private static void createDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL_DATA, USER, PASS)) {
            if (conn != null) {
                System.out.println("Database library created");
                String query = "CREATE DATABASE IF NOT EXISTS library;";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableBooks() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Books created");
                String query = "CREATE TABLE IF NOT EXISTS Books (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "description VARCHAR(255)," +
                        "numberOfPages int," +
                        "state ENUM ('NOT_TAKEN', 'TAKEN')," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableAuthors() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Authors created");
                String query = "CREATE TABLE IF NOT EXISTS Authors (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "surname VARCHAR(255)," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableCustomers() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Customers created");
                String query = "CREATE TABLE IF NOT EXISTS Customers (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "name VARCHAR(255)," +
                        "surname VARCHAR(255)," +
                        "password VARCHAR (255)," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableOrders() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Orders created");
                String query = "CREATE TABLE IF NOT EXISTS Orders (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "returnDate DATE," +
                        "customerId int UNIQUE ," +
                        "bookId int UNIQUE ," +
                        "FOREIGN KEY (customerId) REFERENCES Customers(id) ON DELETE CASCADE ," +
                        "FOREIGN KEY (bookId) REFERENCES Books(id) ON DELETE CASCADE ," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableAuthors_Books() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table Authors_Books created");
                String query = "CREATE TABLE IF NOT EXISTS Authors_Books (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "authorId int," +
                        "bookId int," +
                        "FOREIGN KEY (authorId) REFERENCES Authors(id) ON DELETE CASCADE," +
                        "FOREIGN KEY (bookId) REFERENCES Books(id) ON DELETE CASCADE," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }

    private static void createTableHistory() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {
                System.out.println("Table History created");
                String query = "CREATE TABLE IF NOT EXISTS History (" +
                        "id int NOT NULL AUTO_INCREMENT," +
                        "customerId int," +
                        "orderId int," +
                        "PRIMARY KEY (id)" +
                        ");";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Don't connected something wrong");
            ex.printStackTrace();
        }
    }
}
