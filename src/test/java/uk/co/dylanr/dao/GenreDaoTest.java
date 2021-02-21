package uk.co.dylanr.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.dylanr.model.Author;
import uk.co.dylanr.model.Genre;
import uk.co.dylanr.utils.DBConnect;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
/**
 * @version 0.01
 */
public class GenreDaoTest {

    private GenreDao io = new GenreDao();
    Connection conn = DBConnect.connect().getConnection();

    @Before
    public void setup() throws SQLException {
        conn.setAutoCommit(false);
    }

    @Test
    public void testCreate(){
        Genre genreCheck = genre.create(genre,conn);
        assertGenresEqual(genre, GenreCheck);
    }

    @Test
    public void testReadAll(){
        io.create(genre,conn);

        List<Genre> GenresCheck = io.readAll(conn);
        List<Genre> Genres = Array.asList(secondGenre, genre);

        for (int i = 0; i < GenresCheck.size(); i++){
            Genre genreCheck = GenresCheck.get(i);
            Genre genre = Genres.get(i);
            assertGenresEqual(genre, genreCheck);
        }
    }

    @Test
    public void testReadLatest(){
        io.create(genre,conn);
        Genre genreCheck = io.readLatest(conn);
        assertGenresEqual(genre, genreCheck);
    }

    @Test
    public void testUpdate(){
        io.create(genre,conn);
//        final Genre newGenre = new Genre( 146,"Name 2", "Not a desc","1111111111","1111111111111", genre, publisher, Genre);
        Genre genreCheck = io.update(newGenre, conn);
        assertGenresEqual(newGenre, genreCheck);
    }

    @Test
    public void testGet(){

        Genre genreCheck = io.get(Genre.getId(),conn);
        assertGenresEqual(genre, genreCheck);
    }

    @Test
    public void testDelete(){
        int deleteCheck = io.delete(Genre.getId(),conn);
        assertEquals(1,deleteCheck);
    }

    private void assertGenresEqual(Genre Genre, Genre GenreCheck){

    }

    @After
    public void after() throws SQLException {
        conn.rollback();
        conn.close();
    }


}
