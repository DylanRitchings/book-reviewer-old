package uk.co.dylanr.model;

public class Book {
    private Integer id;
    private String title;
    private String description;
    private String isbn10;
    private String isbn13;
    private Genre genre;
    private Publisher publisher;
    private Author author;

    public Book (int id, String title, String description, String isbn10, String isbn13, Genre genre, Publisher publisher, Author author) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.genre = genre;
        this.publisher = publisher;
        this.author = author;

    }

    public Book (String title, String description, String isbn10, String isbn13, Genre genre, Publisher publisher, Author author) {
        this.title = title;
        this.description = description;
        this.isbn10 = isbn10;
        this.isbn13 = isbn13;
        this.genre = genre;
        this.publisher = publisher;
        this.author = author;

    }

    public Book() {

    }



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Object[] getAll(){
        return new Object[]{id, title, description, isbn10, isbn13, genre.getAll(),  publisher.getAll(),  author.getAll()};
    }

}
