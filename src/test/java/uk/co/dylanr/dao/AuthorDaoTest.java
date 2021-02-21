package uk.co.dylanr.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.dylanr.model.Author;
import uk.co.dylanr.model.Book;
import uk.co.dylanr.utils.DBConnect;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * @version 0.01
 */
public class AuthorDaoTest {

    final Author secondAuthor = new Author(1, "Name1", "Name2" , "Name3");
    Author author = new Author(2, "Jeffrey","Stephington","Jerrard, Matthews");
    private AuthorDao io = new AuthorDao();
    Connection conn = DBConnect.connect().getConnection();           

    @Before
    public void setup() throws SQLException {
        conn.setAutoCommit(false);
    }

    @Test
    public void testCreate(){
       Author authorCheck = Author.create(author,conn);
        assertAuthorsEqual(author, authorCheck);
    }

    @Test
    public void testReadAll(){
        io.create(author,conn);

        List<Author> authorsCheck = io.readAll(conn);
        List<Author> authors = Array.asList(secondAuthor, author);

        for (int i = 0; i < authorsCheck.size(); i++){
            Author authorCheck = authorsCheck.get(i);
            Author author = authors.get(i);
            assertAuthorsEqual(author, authorCheck);
        }
    }

    @Test
    public void testReadLatest(){
        io.create(author,conn);
        Author authorCheck = io.readLatest(conn);
        assertAuthorsEqual(author, authorCheck);
    }

    @Test
    public void testUpdate(){
        io.create(author,conn);
//        final Book newBook = new Book( 146,"Name 2", "Not a desc","1111111111","1111111111111", genre, publisher, author);
        Author authorCheck = io.update(newAuthor, conn);
        assertAuthorsEqual(newAuthor, authorCheck);
    }

    @Test
    public void testGet(){

        Author authorCheck = io.get(author.getId(),conn);
        assertAuthorsEqual(author, authorCheck);
    }

    @Test
    public void testDelete(){
        int deleteCheck = io.delete(author.getId(),conn);
        assertEquals(1,deleteCheck);
    }

    private void assertAuthorsEqual(Author author, Author authorCheck){

    }

    @After
    public void after() throws SQLException{
        conn.rollback();
        conn.close();
    }


}
