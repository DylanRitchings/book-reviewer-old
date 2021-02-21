package uk.co.dylanr.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import uk.co.dylanr.model.Publisher;
import uk.co.dylanr.utils.DBConnect;

import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PublisherDaoTest {


    private PublisherDao io = new PublisherDao();
    Connection conn = DBConnect.connect().getConnection();

    @Before
    public void setup() throws SQLException {
        conn.setAutoCommit(false);
    }

    @Test
    public void testCreate(){
        Publisher publisherCheck = publisher.create(publisher,conn);
        assertPublishersEqual(publisher, PublisherCheck);
    }

    @Test
    public void testReadAll(){
        io.create(publisher,conn);

        List<Publisher> PublishersCheck = io.readAll(conn);
        List<Publisher> Publishers = Array.asList(secondPublisher, publisher);

        for (int i = 0; i < PublishersCheck.size(); i++){
            Publisher publisherCheck = PublishersCheck.get(i);
            Publisher publisher = Publishers.get(i);
            assertPublishersEqual(publisher, publisherCheck);
        }
    }

    @Test
    public void testReadLatest(){
        io.create(publisher,conn);
        Publisher publisherCheck = io.readLatest(conn);
        assertPublishersEqual(publisher, publisherCheck);
    }

    @Test
    public void testUpdate(){
        io.create(publisher,conn);
//        final Publisher newPublisher = new Publisher( 146,"Name 2", "Not a desc","1111111111","1111111111111", publisher, publisher, Publisher);
        Publisher publisherCheck = io.update(newPublisher, conn);
        assertPublishersEqual(newPublisher, publisherCheck);
    }

    @Test
    public void testGet(){

        Publisher publisherCheck = io.get(Publisher.getId(),conn);
        assertPublishersEqual(publisher, publisherCheck);
    }

    @Test
    public void testDelete(){
        int deleteCheck = io.delete(Publisher.getId(),conn);
        assertEquals(1,deleteCheck);
    }

    private void assertPublishersEqual(Publisher Publisher, Publisher PublisherCheck){

    }

    @After
    public void after() throws SQLException {
        conn.rollback();
        conn.close();
    }
}
