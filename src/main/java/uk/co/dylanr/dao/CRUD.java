package uk.co.dylanr.dao;

import java.util.List;

public interface CRUD<T> {

    List<T> readAll();

    T readLatest();

    T create(T t);

    T update(T t);

    int delete(int id);

    T get(int id);
}