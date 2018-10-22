package ch.zhaw.sml.iwi.gpi.hibernate.demo;

import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.Project;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.TimeRecord;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.repository.TimeRecordRepository;
import java.util.Date;
import java.util.List;

public class HibernateDemo {

    public static void main(String[] args) {
        TimeRecordRepository trr = new TimeRecordRepository();

        // Just some playing around ...
        
        Project p = new Project("GPI Vorlesung");
        Employee e = new Employee("Peter Heinrich");
        
        TimeRecord tr = new TimeRecord(p, e, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 8), new Date(System.currentTimeMillis()));
        trr.persist(tr);
        
        e = new Employee("Björn Scheppler");
        
        tr = new TimeRecord(p, e, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 8), new Date(System.currentTimeMillis()));
        trr.persist(tr);
        
        System.out.println("Dump all Entries:");
        List<TimeRecord> timeRecords = trr.findAll();
        for (TimeRecord em : timeRecords) {
            System.out.println("ch.zhaw.sml.iwi.gpi.jdbc.demo.JDBCDemo.main() " + em.getId() + " " + em.getEmployee().getName() + " " + em.getStart() + " " + em.getEnd());
        }
        
        System.out.println("Dump all Entries of Björn:");
        timeRecords = trr.findAllByName("Björn Scheppler");
        for (TimeRecord em : timeRecords) {
            System.out.println("ch.zhaw.sml.iwi.gpi.jdbc.demo.JDBCDemo.main() " + em.getId() + " " + em.getEmployee().getName() + " " + em.getStart() + " " + em.getEnd());
        }

    }
}
