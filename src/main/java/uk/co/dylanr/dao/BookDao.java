package uk.co.dylanr.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.dylanr.model.Author;
import uk.co.dylanr.model.Book;
import uk.co.dylanr.model.Genre;
import uk.co.dylanr.model.Publisher;
import uk.co.dylanr.utils.DBConnect;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDao implements CRUD<Book> {
    private static final Logger LOGGER = LogManager.getLogger();

    private Book extractBookFromResultSet(ResultSet rs) {
        try {
            Book book = new Book();
            book.setId(rs.getInt("book_id"));
            book.setTitle(rs.getString("book_title"));
            book.setDescription(rs.getString("description"));
            book.setIsbn10(rs.getString("isbn10"));
            book.setIsbn13(rs.getString("isbn13"));

            Genre genre = new Genre();
            genre.setId(rs.getInt("genre_id"));
            book.setGenre(genre);

            Publisher publisher = new Publisher();
            publisher.setId(rs.getInt("publisher_id"));
            book.setPublisher(publisher);

            Author author = new Author();
            author.setId(rs.getInt("author_id"));
            book.setAuthor(author);

            return book;
        } catch (SQLException e) {
            LOGGER.error(e);
        }
        return null;
    }

    /**
     *
     * @return all books from books table
     *
     */
    public List<Book> readAll(Connection conn) {
        ResultSet rs = null;
        List<Book> books = new ArrayList<Book>();
        try {
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Books;");
            while (rs.next()) {
                Book book = extractBookFromResultSet(rs);

                books.add(book);
            }

        } catch (SQLException e){
            LOGGER.error(e);
        } finally {
            DBConnect.close(rs);
        }
        return books;
    }



    public Book readLatest(Connection conn) {
//        Connection conn = null;
        ResultSet rs = null;
        Book book = null;
        try {
//             conn = DBConnect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Books ORDER BY book_id DESC LIMIT 1");
            rs.next();
            book = extractBookFromResultSet(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(rs);
//            DBConnect.close(conn);
        }
        return book;
    }

    public Book create(Book book, Connection conn){
            PreparedStatement ps = null;
            Book returnBook = null;
        try {
            ps = conn.prepareStatement("INSERT INTO Books VALUES (NULL, ?, ?, ?, ?, ?, ?, ?)");

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setString(3, book.getIsbn10());
            ps.setString(4, book.getIsbn13());
            ps.setInt(5, book.getGenre().getId());
            ps.setInt(6, book.getPublisher().getId());
            ps.setInt(7, book.getAuthor().getId());
            int i = ps.executeUpdate();

            if(i == 1) {
                returnBook = readLatest(conn);
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(ps);
        }
        return returnBook;
    }



    public Book update(Book book, Connection conn) {
//        Connection conn = null;
        PreparedStatement ps = null;
        Book returnBook = null;
        try {
//            conn = DBConnect.getInstance().getConnection();
            ps = conn.prepareStatement("UPDATE Books " +
                    "SET book_title=?, description=?, isbn10=?, isbn13=?, genre_id=?, publisher_id=?, author_id=?" +
                    " WHERE book_id=?");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getDescription());
            ps.setString(3, book.getIsbn10());
            ps.setString(4, book.getIsbn13());
            ps.setInt(5, book.getGenre().getId());
            ps.setInt(6, book.getPublisher().getId());
            ps.setInt(7, book.getPublisher().getId());
            ps.setInt(8, book.getId());
            int i = ps.executeUpdate();
            if(i == 1) {
                returnBook = get(book.getId(),conn);
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(ps);
//            DBConnect.close(conn);
        }
        return returnBook;
    }


    public int delete(int id, Connection conn) {
        PreparedStatement ps = null;

        int i = 0;
        try {
            ps = conn.prepareStatement("DELETE FROM Books WHERE book_id=?");
            ps.setInt(1, id);
            i = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.close(ps);
        }


        return i;
    }

    /**
     *
     * @return book with id from book table
     *
     */
    public Book get(int id, Connection conn){
            PreparedStatement ps = null;
            ResultSet rs = null;
            Book returnBook = null;
        try {
            ps = conn.prepareStatement("SELECT * FROM Books WHERE book_id=?"); {
                ps.setInt(1, id);
                rs = ps.executeQuery();
                rs.next();
                returnBook = extractBookFromResultSet(rs);
                System.out.println(returnBook);

            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(rs);
            DBConnect.close(ps);
        }
        return returnBook;
    }

    @Override
    public List<Book> readAll(){
        Connection conn = DBConnect.getInstance().getConnection();
        List<Book> books = readAll(conn);
        DBConnect.close(conn);
        return (books);
    }

    @Override
    public Book readLatest(){
        Connection conn = DBConnect.getInstance().getConnection();
        Book book = readLatest(conn);
        DBConnect.close(conn);
        return (book);
    }

    @Override
    public Book create(Book book){
        Connection conn = DBConnect.getInstance().getConnection();
        Book returnBook = create(book, conn);
        DBConnect.close(conn);
        return (returnBook);
    }

    @Override
    public Book update(Book book){
        Connection conn = DBConnect.getInstance().getConnection();
        Book returnBook = update(book, conn);
        DBConnect.close(conn);
        return (returnBook);
    }

    @Override
    public int delete(int id){
        Connection conn = DBConnect.getInstance().getConnection();
        int i = delete(id, conn);
        DBConnect.close(conn);
        return (i);
    }

    @Override
    public Book get(int id){
        Connection conn = DBConnect.getInstance().getConnection();
        Book book = get(id, conn);
        DBConnect.close(conn);
        return (book);
    }
}

