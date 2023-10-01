package com.example.sqlitedemo;

public class Book {
    private int id_book;
    private String title;
    private  Author author;


    public Book(int id_book, String title, Author author) {
        this.id_book = id_book;
        this.title = title;
        this.author = author;
    }

    public int getId_book() {
        return id_book;
    }

    public void setId_book(int id_book) {
        this.id_book = id_book;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book() {
    }
}
