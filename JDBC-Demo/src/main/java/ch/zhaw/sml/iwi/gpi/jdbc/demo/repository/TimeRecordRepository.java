package ch.zhaw.sml.iwi.gpi.jdbc.demo.repository;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.service.DBConnection;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeRecordRepository {

    private static final Logger LOGGER = Logger.getLogger(TimeRecordRepository.class.getName());

    private Connection conn = DBConnection.getConnection();

    public void initializeDBScema() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE TIME_RECORD"
                    + "(id INTEGER not NULL, "
                    + " project_id INTEGER, "
                    + " employee_id INTEGER, "
                    + " start DATETIME, "
                    + " end DATETIME, "
                    + " PRIMARY KEY ( id ), "
                    + "foreign key (project_id) references PROJECT(id), "
                    + "foreign key (employee_id) references EMPLOYEE(id) "
                    + ")";
            stmt.executeUpdate(sql);
            System.out.println("Executed: " + sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
}
