package vn.com.linh.onthi2;

public class Book {
    private int id;
    private String name;
    private int idAuthor;

    public Book() {
    }

    public Book(int id, String name, int idAuthor) {

        this.id = id;
        this.name = name;
        this.idAuthor = idAuthor;
    }

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

    public int getIdAuthor() {
        return idAuthor;
    }

    public void setIdAuthor(int idAuthor) {
        this.idAuthor = idAuthor;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", idAuthor=" + idAuthor +
                '}';
    }
}
