package src.services;

import src.models.Book;
import src.models.State;

import java.sql.*;
import java.util.ArrayList;

import static src.constants.MariaDBConstants.*;

public class BookService {

    private static BookService instance = null;

    public static BookService getInstance() {
        if (instance == null) {
            instance = new BookService();
        }
        return instance;
    }

    public void createBook(Book book) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "INSERT INTO Books(name,description,numberOfPages,state) " +
                        "VALUES (?,?,?,'NOT_TAKEN')";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getDescription());
                preparedStatement.setInt(3, book.getNumberOfPages());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }

    public void addAuthorsToBook(int authorId, int bookId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "INSERT INTO Authors_Books(authorId, bookId) " +
                        "VALUES (?,?)";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, authorId);
                preparedStatement.setInt(2, bookId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }

    public ArrayList<Book> findAllBooks() {

        ArrayList<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Books";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Book book = new Book();
                    book.setName(resultSet.getString("name"));
                    book.setDescription(resultSet.getString("description"));
                    book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                    book.setState(State.valueOf(resultSet.getString("state")));
                    books.add(book);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }

    public ArrayList<Book> findAllNotTakenBooks() {

        ArrayList<Book> books = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Books WHERE state = 'NOT_TAKEN'";

                PreparedStatement preparedStatement = conn.prepareStatement(query);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Book book = new Book();
                    book.setName(resultSet.getString("name"));
                    book.setDescription(resultSet.getString("description"));
                    book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                    book.setState(State.valueOf(resultSet.getString("state")));
                    books.add(book);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return books;
    }

    public Book findBookById(int enteredId) {

        Book book = new Book();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Books WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    book.setName(resultSet.getString("name"));
                    book.setDescription(resultSet.getString("description"));
                    book.setNumberOfPages(resultSet.getInt("numberOfPages"));
                    book.setState(State.valueOf(resultSet.getString("state")));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return book;
    }

    public void updateBook(int enteredId, Book book) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "UPDATE Books SET name = ?, description = ?, numberOfPages = ? WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(4, enteredId);
                preparedStatement.setString(1, book.getName());
                preparedStatement.setString(2, book.getDescription());
                preparedStatement.setInt(3, book.getNumberOfPages());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteBookById(int enteredId) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "DELETE FROM Books WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
