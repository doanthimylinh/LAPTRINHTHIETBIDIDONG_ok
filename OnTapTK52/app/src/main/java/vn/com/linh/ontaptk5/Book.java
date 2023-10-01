package vn.com.linh.ontaptk5;

public class Book {
    private int id;
    private String name;
    private Author author;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }

    public Book(int id, String name, Author author) {
        this.id = id;
        this.name = name;
        this.author = author;
    }
}
