package src.controllers;

import src.models.Book;
import src.services.BookService;

import java.util.ArrayList;
import java.util.Scanner;

public class BookController {

    private static BookController instance = null;
    private final BookService bookService = BookService.getInstance();
    Scanner scanner = new Scanner(System.in);

    public static BookController getInstance() {
        if (instance == null) {
            instance = new BookController();
        }
        return instance;
    }

    public void initAndCreateBook() {
        Book book = new Book();
        System.out.println("Enter book name:");
        book.setName(scanner.nextLine());
        System.out.println("Enter book description:");
        book.setDescription(scanner.nextLine());
        System.out.println("Enter book number of pages:");
        book.setNumberOfPages(Integer.parseInt(scanner.nextLine()));
        bookService.createBook(book);
    }

    public void addAuthorsToBook() {
        System.out.println("Enter book id:");
        int bookId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter author id:");
        int authorId = Integer.parseInt(scanner.nextLine());
        bookService.addAuthorsToBook(authorId, bookId);
    }

    public ArrayList<Book> findAllBooks() {
        return bookService.findAllBooks();
    }

    public Book findBookById() {
        System.out.println("Enter id of book that you want to find:");
        return bookService.findBookById(Integer.parseInt(scanner.nextLine()));
    }

    public ArrayList<Book> findAllNotTakenBooks() {
        return bookService.findAllNotTakenBooks();
    }

    public void updateBook() {
        System.out.println("Enter id of book that you want to update:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter book name:");
        String name = scanner.nextLine();
        System.out.println("Enter book description:");
        String description = scanner.nextLine();
        System.out.println("Enter number of pages:");
        int numberOfPages = Integer.parseInt(scanner.nextLine());
        Book book = new Book();
        book.setName(name);
        book.setDescription(description);
        book.setNumberOfPages(numberOfPages);
        bookService.updateBook(id, book);
    }

    public void deleteBookById() {
        System.out.println("Enter book id that you want to delete:");
        bookService.deleteBookById(Integer.parseInt(scanner.nextLine()));
    }
}
