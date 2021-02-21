package uk.co.dylanr.dao;

import uk.co.dylanr.model.Publisher;

import java.util.List;

public class PublisherDao implements CRUD<Publisher>{
    @Override
    public List<Publisher> readAll() {
        return null;
    }

    @Override
    public Publisher readLatest() {
        return null;
    }

    @Override
    public Publisher create(Publisher publisher) {
        return null;
    }

    @Override
    public Publisher update(Publisher publisher) {
        return null;
    }

    @Override
    public int delete(int id) {
        return 0;
    }

    @Override
    public Publisher get(int id) {
        return null;
    }
}
