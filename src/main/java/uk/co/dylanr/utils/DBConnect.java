package uk.co.dylanr.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.sql.*;
import java.util.Properties;

/**
 * @version 0.01
 */
public class DBConnect {

    private static final Logger LOGGER = LogManager.getLogger();
    private final String url;
    private final String user;
    private final String pass;

    /**
     * Fills DB details from properties
     *
     * @param properties
     */
    private DBConnect(String properties)  {
        Properties p = new Properties();
        try {
            FileReader reader = new FileReader(properties);
            p.load(reader);
        } catch (Exception e){

            LOGGER.error(e);
        }

        this.url = p.getProperty("db.url", "");
        this.user = p.getProperty("db.user", "");
        this.pass = p.getProperty("db.pass", "");
    }


    public DBConnect() {
        this("src/main/resources/db.properties");
    }
//    private static String driver =

    /**
     * @return Connection to database
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection(url, user, pass);
        } catch (SQLException ex) {
            throw new RuntimeException("Error connecting to the database", ex);
        }
    }



    private static DBConnect instance;

    /**
     * Creates instance of DBConnect
     */
    public static DBConnect connect() {
        instance = new DBConnect();
        return instance;
    }

    /**
     * TODO
     * @param properties
     * @return
     */
    public static DBConnect connect(String properties) {
        instance = new DBConnect(properties);
        return instance;
    }

    /**
     * TODO
     * @return
     */
    public static DBConnect getInstance() {
        if (instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    /**
     * Closes connection to database
     */
    public static void close(Connection conn){
        assert conn!=null;
        try {
            conn.close();
        } catch (SQLException e){
            LOGGER.error(e);
        }
    }

    /**
     * Closes result set
     */
    public static void close(ResultSet rs){
        assert rs!=null;
        try {
            rs.close();
        } catch (SQLException e){
            LOGGER.error(e);
        }
    }

    /**
     * Closes prepared statement
     */
    public static void close(PreparedStatement ps){
        assert ps!=null;
        try {
            ps.close();
        } catch (SQLException e){
            LOGGER.error(e);
        }
    }
}