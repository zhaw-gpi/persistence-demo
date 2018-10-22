package ch.zhaw.sml.iwi.gpi.jdbc.demo.repository;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.service.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EmployeeRepository {

    private static final Logger LOGGER = Logger.getLogger(EmployeeRepository.class.getName());

    private final Connection conn;

    public EmployeeRepository() {
        this.conn = DBConnection.getConnection();
    }

    public void initializeDBScema() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE EMPLOYEE"
                    + "(id INTEGER auto_increment, "
                    + " name VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Executed: " + sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private static final String PERSIST_SQL = "INSERT INTO EMPLOYEE SET NAME = ?";
    public Long persist(Employee e) {
        try {
            PreparedStatement stmt = conn.prepareStatement(PERSIST_SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, e.getName());
            stmt.executeUpdate();
            ResultSet generatedId = stmt.getGeneratedKeys();
            if (generatedId.next()) {
                e.setId(generatedId.getLong(1));
                return generatedId.getLong(1);
            } else {
                return (long) - 1;
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return (long) - 1;
    }

    private static final String FIND_BY_ID_SQL = "SELECT * FROM EMPLOYEE WHERE ID = ?";
    public Employee findById(Long id) {
        Employee result;
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID_SQL);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Employee(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String FIND_ALL_SQL = "SELECT * FROM EMPLOYEE";
    public List<Employee> findAll() {
        List<Employee> results = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Employee e = new Employee(rs.getLong("id"), rs.getString("name"));
                results.add(e);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return results;
    }
}
