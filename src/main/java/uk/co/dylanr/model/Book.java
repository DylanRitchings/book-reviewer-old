package uk.co.dylanr.model;
public class Book {
    int id;
    String title;
    String description;
    String isbn10;
    String isbn13;

    public Book(int id, String title, String description, String isbn10, String isbn13) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIsbn10() {
        return isbn10;
    }

    public void setIsbn10(String isbn10) {
        this.isbn10 = isbn10;
    }

    public String getIsbn13() {
        return isbn13;
    }

    public void setIsbn13(String isbn13) {
        this.isbn13 = isbn13;
    }
}
