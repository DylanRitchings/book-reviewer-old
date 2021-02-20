package uk.co.dylanr.utils;

import org.junit.Test;

import java.sql.Connection;

public class DBConnectTest {
    Connection conn;
    @Test
    public void connectionTest() {
        conn = DBConnect.connect().getConnection();
        DBConnect.close(conn);
    }
}
