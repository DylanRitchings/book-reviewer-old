package uk.co.dylanr.dao;

import uk.co.dylanr.model.Author;

import java.util.List;
/**
 * @version 0.01
 */
public class AuthorDao implements CRUD<Author>{
    @Override
    public List<Author> readAll() {
        return null;
    }

    @Override
    public Author readLatest() {
        return null;
    }

    @Override
    public Author create(Author author) {
        return null;
    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public Author get(int id) {
        return null;
    }
}
