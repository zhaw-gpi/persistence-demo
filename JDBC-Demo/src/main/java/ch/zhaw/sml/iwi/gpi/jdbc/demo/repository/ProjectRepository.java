package ch.zhaw.sml.iwi.gpi.jdbc.demo.repository;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.service.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProjectRepository {

    private static final Logger LOGGER = Logger.getLogger(ProjectRepository.class.getName());

    private Connection conn = DBConnection.getConnection();

    public void initializeDBScema() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE PROJECT"
                    + "(id INTEGER not NULL, "
                    + " name VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Executed: " + sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
