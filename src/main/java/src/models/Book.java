package src.models;

import java.util.Objects;

public class Book {

    private String name;
    private String description;
    private int numberOfPages;
    private State state;

    public Book() {
        this.state = State.NOT_TAKEN;
    }

    public Book(String name, String description, int numberOfPages) {
        this.name = name;
        this.description = description;
        this.numberOfPages = numberOfPages;
        this.state = State.NOT_TAKEN;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return numberOfPages == book.numberOfPages &&
                Objects.equals(name, book.name) &&
                Objects.equals(description, book.description) &&
                state == book.state;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, numberOfPages, state);
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", state=" + state +
                '}';
    }
}
