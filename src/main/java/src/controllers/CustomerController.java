package src.controllers;

import src.models.Customer;
import src.services.CustomerService;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerController {

    private static CustomerController instance = null;
    private final CustomerService customerService = CustomerService.getInstance();
    Scanner scanner = new Scanner(System.in);

    public static CustomerController getInstance() {
        if (instance == null) {
            instance = new CustomerController();
        }
        return instance;
    }

    public void initAndCreateCustomer() {
        Customer customer = new Customer();
        System.out.println("Enter customer name:");
        customer.setName(scanner.nextLine());
        System.out.println("Enter customer surname:");
        customer.setSurname(scanner.nextLine());
        System.out.println("Enter customer password:");
        customer.setPassword(scanner.nextLine());
        customerService.createCustomer(customer);
    }

    public ArrayList<Customer> findAllCustomers() {
        return customerService.findAllCustomers();
    }

    public Customer findCustomerById() {
        System.out.println("Enter id of customer that you want to find:");
        return customerService.findCustomerById(Integer.parseInt(scanner.nextLine()));
    }

    public void updateCustomer() {
        System.out.println("Enter id of customer that you want to update:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter customer surname:");
        String surname = scanner.nextLine();
        System.out.println("Enter customer password:");
        String password = scanner.nextLine();
        Customer customer = new Customer(name, surname, password);
        customerService.updateCustomer(id, customer);
    }

    public void deleteCustomerById() {
        System.out.println("Enter customer id that you want to delete:");
        customerService.deleteCustomerById(Integer.parseInt(scanner.nextLine()));
    }
}
