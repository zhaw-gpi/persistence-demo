/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.zhaw.sml.iwi.gpi.jdbc.demo;

import ch.zhaw.sml.iwi.gpi.jdbc.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.repository.EmployeeRepository;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.repository.ProjectRepository;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.repository.TimeRecordRepository;
import ch.zhaw.sml.iwi.gpi.jdbc.demo.service.DBConnection;
import java.sql.Statement;

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

        Statement stmt = DBConnection.getConnection().createStatement();
        String sql = "INSERT INTO Employee SET id=1, name='Peter Heinrich'";
        stmt.executeUpdate(sql);
        stmt = DBConnection.getConnection().createStatement();
        sql = "INSERT INTO Employee SET id=2, name='Björn Scheppler'";
        stmt.executeUpdate(sql);

        for (Employee e : er.findAll()) {
            System.out.println("ch.zhaw.sml.iwi.gpi.jdbc.demo.JDBCDemo.main() " + e.getName());
        }

    }
}
