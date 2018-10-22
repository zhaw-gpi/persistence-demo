package ch.zhaw.sml.iwi.gpi.jdbc.demo.repository;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.Project;
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

public class ProjectRepository {

    private static final Logger LOGGER = Logger.getLogger(ProjectRepository.class.getName());

    private final Connection conn = DBConnection.getConnection();
    
    public void initializeDBScema() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE PROJECT"
                    + "(id INTEGER auto_increment, "
                    + " name VARCHAR(255), "
                    + " PRIMARY KEY ( id ))";
            stmt.executeUpdate(sql);
            System.out.println("Executed: " + sql);
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }
    
    private static final String PERSIST_SQL = "INSERT INTO PROJECT SET NAME = ?";
    public Long persist(Project p) {
        try {
            PreparedStatement stmt = conn.prepareStatement(PERSIST_SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, p.getName());
            stmt.executeUpdate();
            ResultSet generatedId = stmt.getGeneratedKeys();
            if (generatedId.next()) {
                p.setId(generatedId.getLong(1));
                return generatedId.getLong(1);
            } else {
                return (long) - 1;
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return (long) - 1;
    }

    private static final String FIND_BY_ID_SQL = "SELECT * FROM PROJECT WHERE ID = ?";
    public Project findById(Long id) {
        Project result;
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID_SQL);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Project(rs.getLong("id"), rs.getString("name"));
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String FIND_ALL_SQL = "SELECT * FROM PROJECT";

    public List<Project> findAll() {
        List<Project> results = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Project p = new Project(rs.getLong("id"), rs.getString("name"));
                results.add(p);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return results;
    }
}
