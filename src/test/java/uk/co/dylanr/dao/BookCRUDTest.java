package uk.co.dylanr.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.dylanr.model.Author;
import uk.co.dylanr.model.Book;
import uk.co.dylanr.model.Genre;
import uk.co.dylanr.model.Publisher;

import static org.junit.Assert.assertEquals;

public class BookCRUDTest {
    private final BookCRUD io = new BookCRUD();
    final Author author = new Author(1, "Name1", "Name2" , "Name3");
    final Genre genre = new Genre(1,"Genre Name");
    final Publisher publisher = new Publisher (1, "Publisher Name");
    final Book book = new Book( "Book Name", "This is a description","0000000000","0000000000000", genre, publisher, author);



    @Before
    public void setup() {

//        DBConnect.connect().getConnection();

    }
    @Test
    public void testCreate() {
        Book bookCheck = io.create(book);

//        assertArrayEquals(book.getAll(), bookCheck.getAll());
        assertEquals(book.getAuthor().getId(), bookCheck.getAuthor().getId());
        assertEquals(book.getPublisher().getId(), bookCheck.getPublisher().getId());
        assertEquals(book.getGenre().getId(), bookCheck.getGenre().getId());

        assertEquals(book.getTitle(), bookCheck.getTitle());
        assertEquals(book.getDescription(), bookCheck.getDescription());
        assertEquals(book.getIsbn10(), bookCheck.getIsbn10());

        assertEquals(book.getIsbn13(), bookCheck.getIsbn13());

    }

    @Test
    public void testReadAll(){

    }

    @Test
    public void testReadLatest(){

    }

    @Test
    public void testUpdate(){

    }

    @Test
    public void testGet(){

    }

    @Test
    public void testDelete(){
        int deleteCheck = io.delete(book.getId());
        //TODO DO
    }

    @After
    public void after() {
//        DBConnect.close();
    }



}
