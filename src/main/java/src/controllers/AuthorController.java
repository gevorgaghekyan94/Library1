package src.controllers;

import src.models.Author;
import src.services.AuthorService;

import java.util.ArrayList;
import java.util.Scanner;

public class AuthorController {

    private static AuthorController instance = null;
    private final AuthorService authorService = AuthorService.getInstance();
    Scanner scanner = new Scanner(System.in);

    public static AuthorController getInstance() {
        if (instance == null) {
            instance = new AuthorController();
        }
        return instance;
    }

    public void initAndCreateAuthor() {
        Author author = new Author();
        System.out.println("Enter author name:");
        author.setName(scanner.nextLine());
        System.out.println("Enter author surname:");
        author.setSurname(scanner.nextLine());
        authorService.createAuthor(author);
    }

    public ArrayList<Author> findAllAuthors() {
        return authorService.findAllAuthors();
    }

    public Author findAuthorById() {
        System.out.println("Enter id of author that you want to find:");
        return authorService.findAuthorById(Integer.parseInt(scanner.nextLine()));
    }

    public void updateAuthor() {
        System.out.println("Enter id of author that you want to update:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter author name:");
        String name = scanner.nextLine();
        System.out.println("Enter author surname:");
        String surname = scanner.nextLine();
        Author author = new Author(name, surname);
        authorService.updateAuthor(id, author);
    }

    public void deleteAuthorById() {
        System.out.println("Enter author id that you want to delete:");
        authorService.deleteAuthorById(Integer.parseInt(scanner.nextLine()));
    }
}
