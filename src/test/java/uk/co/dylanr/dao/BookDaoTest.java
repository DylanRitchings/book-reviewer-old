package uk.co.dylanr.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.dylanr.model.Author;
import uk.co.dylanr.model.Book;
import uk.co.dylanr.model.Genre;
import uk.co.dylanr.model.Publisher;
import uk.co.dylanr.utils.DBConnect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookDaoTest {
    private BookDao io = new BookDao();
    final Author author = new Author(1, "Name1", "Name2" , "Name3");
    final Genre genre = new Genre(1,"Genre Name");
    final Publisher publisher = new Publisher (1, "Publisher Name");
    final Book book = new Book( 1,"Book Name", "This is a description","0000000000","0000000000000", genre, publisher, author);
    final Book secondBook = new Book( 146,"title", "description","1234567890","1234567890123", genre, publisher, author);
    Connection conn = DBConnect.connect().getConnection();




    @Before
    public void setup() throws Exception {
        conn.setAutoCommit(false);

    }
    @Test
    public void testCreate() {

        Book bookCheck = io.create(book, conn);
        assertBooksEqual(book, bookCheck);

    }


    @Test
    public void testReadAll(){
        io.create(book,conn);

        List<Book> booksCheck = io.readAll(conn);
        List<Book> books = Arrays.asList(secondBook, book);

        for (int i = 0; i < booksCheck.size(); i++){
            Book bookCheck = booksCheck.get(i);
            Book book = books.get(i);
            assertBooksEqual(book, bookCheck);
        }

    }


    @Test
    public void testReadLatest(){
        io.create(book,conn);
        Book bookCheck = io.readLatest(conn);
        assertBooksEqual(book, bookCheck);
    }

    @Test
    public void testUpdate(){
        io.create(book,conn);
        final Book newBook = new Book( 146,"Name 2", "Not a desc","1111111111","1111111111111", genre, publisher, author);
        Book bookCheck = io.update(newBook, conn);
        assertBooksEqual(newBook, bookCheck);
    }

    @Test
    public void testGet(){

        Book bookCheck = io.get(secondBook.getId(),conn);
        assertBooksEqual(secondBook, bookCheck);
    }

    @Test
    public void testDelete(){
        int deleteCheck = io.delete(secondBook.getId(),conn);
        assertEquals(1,deleteCheck);
    }



    private void assertBooksEqual(Book book, Book bookCheck){
        assertEquals(book.getTitle(), bookCheck.getTitle());
        assertEquals(book.getDescription(), bookCheck.getDescription());
        assertEquals(book.getIsbn10(), bookCheck.getIsbn10());
        assertEquals(book.getIsbn13(), bookCheck.getIsbn13());

        assertEquals(book.getAuthor().getId(), bookCheck.getAuthor().getId());
        assertEquals(book.getPublisher().getId(), bookCheck.getPublisher().getId());
        assertEquals(book.getGenre().getId(), bookCheck.getGenre().getId());
    }

    @After
    public void after() throws SQLException {
        conn.rollback();
        conn.close();

    }

}
