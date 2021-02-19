package uk.co.dylanr.model;

import java.util.List;

public class Publisher {
    private Integer id;
    private String name;
    private List<Book> books;

    public Publisher(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public Publisher(){};
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Object[] getAll(){
        return new Object[]{id, name, books};
    }
}
