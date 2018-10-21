package ch.zhaw.sml.iwi.gpi.jdbc.demo.repository;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.service.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeRepository {

    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    private Connection conn = DBConnection.getConnection();

    public void initializeDBScema() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE EMPLOYEE"
                    + "(id INTEGER not NULL, "
                    + " name VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Executed: " + sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    
    private static final String FIND_ALL_SQL = "SELECT * FROM EMPLOYEE";
    public List<Employee> findAll() {
        List<Employee> results = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL_SQL);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Employee e = new Employee(rs.getLong("id"), rs.getString("name"));
                results.add(e);
            }     
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return results;
    }
}
