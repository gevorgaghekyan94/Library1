package src.controllers;

import src.services.LibraryService;

import java.util.Scanner;

public class LibraryController {

    private static LibraryController instance = null;
    private final LibraryService libraryService = LibraryService.getInstance();
    Scanner scanner = new Scanner(System.in);

    public static LibraryController getInstance() {
        if (instance == null) {
            instance = new LibraryController();
        }
        return instance;
    }

    public void takeBookFromLibrary() {
        System.out.println("Enter customer id:");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter customer password:");
        String password = scanner.nextLine();
        if (libraryService.checkPassword(customerId, password)) {
            System.out.println("Logged in");
            System.out.println("Enter book id:");
            int bookId = Integer.parseInt(scanner.nextLine());
            libraryService.TakeBookFromLibrary(customerId, bookId);
        } else {
            System.out.println("INCORRECT PASSWORD!!!");
        }
    }

    public void returnBookToLibrary() {
        System.out.println("Enter customer id:");
        int customerId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter book id:");
        int bookId = Integer.parseInt(scanner.nextLine());
        libraryService.returnBookToLibrary(customerId, bookId);
    }
}
