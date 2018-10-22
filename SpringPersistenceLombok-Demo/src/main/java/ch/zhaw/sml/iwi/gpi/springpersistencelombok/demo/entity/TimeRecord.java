package ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
public class TimeRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Project project;
    
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Employee employee;

    @Temporal(TemporalType.TIMESTAMP)
    private Date start;

    @Temporal(TemporalType.TIMESTAMP)
    private Date end;

    public TimeRecord() {
    }

    public TimeRecord(Project project, Employee employee, Date start, Date end) {
        this.project = project;
        this.employee = employee;
        this.start = start;
        this.end = end;
    }

}
