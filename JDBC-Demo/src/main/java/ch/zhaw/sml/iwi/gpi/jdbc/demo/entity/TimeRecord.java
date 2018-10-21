package ch.zhaw.sml.iwi.gpi.jdbc.demo.entity;

import java.util.Date;

public class TimeRecord {
    private Long id;
    private Project project;
    private Employee employee;
    private Date start;
    private Date end;

    public TimeRecord() {
    }
    
    public TimeRecord(Project project, Employee employee, Date start, Date end) {
        this.project = project;
        this.employee = employee;
        this.start = start;
        this.end = end;
    }
 
    public TimeRecord(Long id, Project project, Employee employee, Date start, Date end) {
        this.id = id;
        this.project = project;
        this.employee = employee;
        this.start = start;
        this.end = end;
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

}
