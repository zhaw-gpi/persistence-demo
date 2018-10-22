/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.zhaw.sml.iwi.gpi.hibernate.demo;

import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.Project;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.TimeRecord;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.repository.TimeRecordRepository;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author peterhe
 */
public class HibernateDemo {

    public static void main(String[] args) {
        TimeRecordRepository trr = new TimeRecordRepository();

        Employee e = new Employee("Björn Scheppler");
        Project p = new Project("GPI Vorlesung");
        TimeRecord tr = new TimeRecord(p, e, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 8), new Date(System.currentTimeMillis()));

        trr.persist(tr);

        List<TimeRecord> timeRecords = trr.findAll();
        for (TimeRecord em : timeRecords) {
            System.out.println("ch.zhaw.sml.iwi.gpi.jdbc.demo.JDBCDemo.main() " + em.getId() + " " + em.getEmployee().getId() + " " + em.getStart() + " " + em.getEnd());
        }

    }
}
