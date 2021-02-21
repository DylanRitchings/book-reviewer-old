package uk.co.dylanr.dao;

import uk.co.dylanr.model.Genre;

import java.util.List;

public class GenreDao implements CRUD<Genre>{
    @Override
    public List<Genre> readAll() {
        return null;
    }

    @Override
    public Genre readLatest() {
        return null;
    }

    @Override
    public Genre create(Genre genre) {
        return null;
    }

    @Override
    public Genre update(Genre genre) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public Genre get(int id) {
        return null;
    }
}
