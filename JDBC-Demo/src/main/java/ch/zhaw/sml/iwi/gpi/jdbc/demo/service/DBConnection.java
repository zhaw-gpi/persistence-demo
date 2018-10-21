package ch.zhaw.sml.iwi.gpi.jdbc.demo.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    
    private static Connection dbConn;
    private static final Logger LOGGER = Logger.getLogger(DBConnection.class.getName()); 
    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String DB_URL = "jdbc:h2:mem:test";
    private static final String DB_USER = "sa";
    private static final String DB_PASS = "";
    
    public static Connection getConnection() {
        
        if(dbConn == null) {
            try {
                Class.forName (DB_DRIVER); 
                dbConn =  DriverManager.getConnection (DB_URL, DB_USER, DB_PASS);
            } catch (SQLException | ClassNotFoundException ex) {
               LOGGER.log(Level.SEVERE, null, ex);
            }
        }
        
        return dbConn;
    }
}
