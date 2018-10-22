/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.zhaw.sml.iwi.gpi.jdbc.demo;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.Project;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.TimeRecord;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.repository.EmployeeRepository;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.repository.ProjectRepository;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.repository.TimeRecordRepository;
import java.util.Date;
import java.util.List;

/**
 *
 * @author peterhe
 */
public class JDBCDemo {

    public static void main(String[] args) throws Exception {
        EmployeeRepository er = new EmployeeRepository();
        ProjectRepository pr = new ProjectRepository();
        TimeRecordRepository trr = new TimeRecordRepository();
        er.initializeDBScema();
        pr.initializeDBScema();
        trr.initializeDBScema();

        // Just some playing around ...
        
        Employee e = new Employee("Peter Heinrich");
        er.persist(e);

        e = new Employee("Björn Scheppler");
        er.persist(e);

        Project p = new Project("GPI Vorlesung");
        pr.persist(p);

        TimeRecord tr = new TimeRecord(p, e, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 8), new Date(System.currentTimeMillis()));
        trr.persist(tr);

        List<TimeRecord> timeRecords = trr.findAll();
        for (TimeRecord em : timeRecords) {
            System.out.println("ch.zhaw.sml.iwi.gpi.jdbc.demo.JDBCDemo.main() " + em.getId() + " " + em.getEmployee().getId() + " " + em.getStart() + " " + em.getEnd());
        }

    }
}
