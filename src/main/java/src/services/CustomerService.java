package src.services;

import src.models.Customer;

import java.sql.*;
import java.util.ArrayList;

import static src.constants.MariaDBConstants.*;

public class CustomerService {

    private static CustomerService instance = null;

    public static CustomerService getInstance() {
        if (instance == null) {
            instance = new CustomerService();
        }
        return instance;
    }

    public void createCustomer(Customer customer) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "INSERT INTO Customers(name, surname, password) " +
                        "VALUES (?,?,?);";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSurname());
                preparedStatement.setString(3, customer.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            System.out.println("Something goes wrong");
            ex.printStackTrace();
        }
    }

    public ArrayList<Customer> findAllCustomers() {

        ArrayList<Customer> customers = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Customers";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    Customer customer = new Customer();
                    customer.setName(resultSet.getString("name"));
                    customer.setSurname(resultSet.getString("surname"));
                    customer.setPassword(resultSet.getString("password"));
                    customers.add(customer);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customers;
    }

    public Customer findCustomerById(int enteredId) {

        Customer customer = new Customer();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "SELECT * FROM Customers WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);

                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    customer.setName(resultSet.getString("name"));
                    customer.setSurname(resultSet.getString("surname"));
                    customer.setPassword(resultSet.getString("password"));
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return customer;
    }

    public void updateCustomer(int enteredId, Customer customer) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "UPDATE Customers SET name = ?, surname = ?, password = ?  WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(4, enteredId);
                preparedStatement.setString(1, customer.getName());
                preparedStatement.setString(2, customer.getSurname());
                preparedStatement.setString(3, customer.getPassword());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void deleteCustomerById(int enteredId) {

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            if (conn != null) {

                String query = "DELETE FROM Customers WHERE id = ?";

                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setInt(1, enteredId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
