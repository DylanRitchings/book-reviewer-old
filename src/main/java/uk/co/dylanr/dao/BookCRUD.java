package uk.co.dylanr.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uk.co.dylanr.model.Author;
import uk.co.dylanr.model.Book;
import uk.co.dylanr.model.Genre;
import uk.co.dylanr.model.Publisher;
import uk.co.dylanr.utils.DBConnect;

import java.sql.*;
import java.util.List;

public class BookCRUD implements CRUD<Book> {
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
    @Override
    public List<Book> readAll() {
        Connection conn = null;
        ResultSet rs = null;
        List<Book> books = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String read = "SELECT * FROM Books;";
            rs = stmt.executeQuery(read);
            while (rs.next()) {
                Book book = extractBookFromResultSet(rs);
                books.add(book);
            }

        } catch (SQLException e){
            LOGGER.error(e);
        } finally {
            DBConnect.close(rs);
            DBConnect.close(conn);
        }
        return books;
    }


    @Override
    public Book readLatest() {
        Connection conn = null;
        ResultSet rs = null;
        Book book = null;
        try {
             conn = DBConnect.getInstance().getConnection();
            Statement stmt = conn.createStatement();
            String read = "SELECT * FROM Books;";
            rs = stmt.executeQuery(read);
            rs.next();
            book = extractBookFromResultSet(rs);
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(rs);
            DBConnect.close(conn);
        }
        return book;
    }

    @Override
    public Book create(Book book){
            Connection conn = null;
            PreparedStatement ps = null;
            Book returnBook = null;
        try {
            conn = DBConnect.getInstance().getConnection();
            ps = conn.prepareStatement("INSERT INTO Books VALUES (?, ?, ?, ?, ?, ?, ?, ?)");

            if (book.getId()==null){
                ps.setNull(1,Types.INTEGER);
            } else {
                ps.setInt(1, book.getId());
            }
            ps.setString(2, book.getTitle());
            ps.setString(3, book.getDescription());
            ps.setString(4, book.getIsbn10());
            ps.setString(5, book.getIsbn13());
            ps.setInt(6, book.getGenre().getId());
            ps.setInt(7, book.getPublisher().getId());
            ps.setInt(8, book.getAuthor().getId());
            int i = ps.executeUpdate();
            if(i == 1) {
                returnBook = readLatest();
            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(ps);
            DBConnect.close(conn);
        }
        return returnBook;
    }



    @Override
    public Book update(Book book) {
        Connection conn = null;
        PreparedStatement ps = null;
        Book returnBook = null;
        try {
            conn = DBConnect.getInstance().getConnection();
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
                returnBook = readLatest();
            }

        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(ps);
            DBConnect.close(conn);
        }
        return returnBook;
    }

    @Override
    public int delete(int id) {
        Connection conn = null;
        PreparedStatement ps = null;

        int i = 0;
        try {
            conn = DBConnect.getInstance().getConnection();
            ps = conn.prepareStatement("DELETE FROM Books WHERE id=?");
            ps.setInt(1, id);
            i = ps.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            DBConnect.close(ps);
            DBConnect.close(conn);
        }


        return i;
    }

    /**
     *
     * @return book with id from book table
     *
     */
    @Override
    public Book get(int id){
            Connection conn = null;
            PreparedStatement ps = null;
            ResultSet rs = null;
            Book returnBook = null;
        try {
            conn = DBConnect.getInstance().getConnection();
//            Statement s = conn.createStatement();
//            rs = s.executeQuery("SELECT * FROM books WHERE book_id=?");
            ps = conn.prepareStatement("SELECT * FROM books WHERE book_id=?"); {
                ps.setInt(1, id);
                rs = ps.executeQuery();
                rs.next();
                returnBook = extractBookFromResultSet(rs);

            }
        } catch (SQLException e) {
            LOGGER.error(e);
        } finally {
            DBConnect.close(rs);
            DBConnect.close(ps);
            DBConnect.close(conn);
        }
        return returnBook;
    }
}
