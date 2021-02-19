package uk.co.dylanr.service;

import uk.co.dylanr.dao.CRUD;

/**
 * Dependancy inversion example
 */
public class TestService {

    private final CRUD<Object> test;


    public TestService(CRUD<Object> test) {
        this.test = test;
        test.get(1);
    }
}
