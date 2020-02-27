package src.services;

import src.models.Author;

import java.sql.*;
import java.util.ArrayList;

import static src.constants.MariaDBConstants.*;

public class AuthorService {

    private static AuthorService instance = null;

    public static AuthorService getInstance() {
        if (instance == null) {
            instance = new AuthorService();
        }
        return instance;
    }

    public void createAuthor(Author author) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "INSERT INTO Authors(name, surname) " +
                        "VALUES (?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }

    public ArrayList<Author> findAllAuthors() {

        ArrayList<Author> authors = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Authors";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Author author = new Author();
                    author.setName(resultSet.getString("name"));
                    author.setSurname(resultSet.getString("surname"));
                    authors.add(author);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return authors;
    }

    public Author findAuthorById(int enteredId) {

        Author author = new Author();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Authors WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    author.setName(resultSet.getString("name"));
                    author.setSurname(resultSet.getString("surname"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return author;
    }

    public void updateAuthor(int enteredId, Author author) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "UPDATE Authors SET name = ?, surname = ? WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(3, enteredId);
                preparedStatement.setString(1, author.getName());
                preparedStatement.setString(2, author.getSurname());

                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteAuthorById(int enteredId) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "DELETE FROM Authors WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
