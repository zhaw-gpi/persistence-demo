package ch.zhaw.sml.iwi.gpi.jdbc.demo.repository;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.TimeRecord;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.service.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TimeRecordRepository {

    private static final Logger LOGGER = Logger.getLogger(TimeRecordRepository.class.getName());

    private final Connection conn = DBConnection.getConnection();
    private final EmployeeRepository employeeRepository = new EmployeeRepository();
    private final ProjectRepository projectRepository = new ProjectRepository();

    public void initializeDBScema() {
        try {
            Statement stmt = conn.createStatement();
            String sql = "CREATE TABLE TIME_RECORD"
                    + "(id INTEGER auto_increment, "
                    + " project_id INTEGER, "
                    + " employee_id INTEGER, "
                    + " start TIMESTAMP, "
                    + " end TIMESTAMP, "
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

    private static final String PERSIST_SQL = "INSERT INTO TIME_RECORD SET project_id = ?, employee_id = ?, start = ?, end = ?";

    public Long persist(TimeRecord p) {
        try {
            PreparedStatement stmt = conn.prepareStatement(PERSIST_SQL, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, p.getProject().getId());
            stmt.setLong(2, p.getEmployee().getId());
            stmt.setTimestamp(3, new Timestamp(p.getStart().getTime()));
            stmt.setTimestamp(4, new Timestamp(p.getEnd().getTime()));
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

    private static final String FIND_BY_ID_SQL = "SELECT * FROM TIME_RECORD WHERE ID = ?";

    public TimeRecord findById(Long id) {
        TimeRecord result;
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_BY_ID_SQL);
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new TimeRecord(
                        rs.getLong("id"),
                        projectRepository.findById(rs.getLong("project_id")),
                        employeeRepository.findById(rs.getLong("employee_id")),
                        new Date(rs.getTimestamp("start").getTime()),
                        new Date(rs.getTimestamp("end").getTime())
                );
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    private static final String FIND_ALL_SQL = "SELECT * FROM TIME_RECORD";

    public List<TimeRecord> findAll() {
        List<TimeRecord> results = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_ALL_SQL);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TimeRecord tr = new TimeRecord(
                        rs.getLong("id"),
                        projectRepository.findById(rs.getLong("project_id")),
                        employeeRepository.findById(rs.getLong("employee_id")),
                        new Date(rs.getTimestamp("start").getTime()),
                        new Date(rs.getTimestamp("end").getTime())
                );
                results.add(tr);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return results;
    }
    
    private static final String FIND_BY_NAME_SQL = "SELECT TIME_RECORD.* FROM TIME_RECORD JOIN EMPLOYEE ON TIME_RECORD.employee_id=EMPLOYEE.id WHERE EMPLOYEE.name = ?";

    public List<TimeRecord> findAllByName(String name) {
        List<TimeRecord> results = new ArrayList<>();
        try {
            PreparedStatement stmt = conn.prepareStatement(FIND_BY_NAME_SQL);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                TimeRecord tr = new TimeRecord(
                        rs.getLong("id"),
                        projectRepository.findById(rs.getLong("project_id")),
                        employeeRepository.findById(rs.getLong("employee_id")),
                        new Date(rs.getTimestamp("start").getTime()),
                        new Date(rs.getTimestamp("end").getTime())
                );
                results.add(tr);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return results;
    }
}
