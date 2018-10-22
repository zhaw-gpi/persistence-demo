package ch.zhaw.sml.iwi.gpi.hibernate.demo.repository;

import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.Project;
import ch.zhaw.sml.iwi.gpi.hibernate.demo.entity.TimeRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class TimeRecordRepository {

    private static final Logger LOGGER = Logger.getLogger(TimeRecordRepository.class.getName());
    private static SessionFactory factory;

    public TimeRecordRepository() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Employee.class);
        configuration.addAnnotatedClass(Project.class);
        configuration.addAnnotatedClass(TimeRecord.class);
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        factory = configuration.buildSessionFactory(serviceRegistry);
    }

    public void persist(TimeRecord tr) {
        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        if (tx == null) {
            return;
        }

        try {
            Long newId = (Long) session.save(tr);
            tr.setId(newId);
            tx.commit();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            tx.rollback();
        }
        session.close();

    }

    public List<TimeRecord> findAll() {
        List<TimeRecord> result = new ArrayList<>();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        if (tx == null) {
            return null;
        }

        try {
            result = session.createQuery("SELECT T FROM TimeRecord AS T").list();
            tx.commit();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            tx.rollback();
        }
        session.close();
        return result;
    }

    public List<TimeRecord> findAllByName(String name) {
        List<TimeRecord> result = new ArrayList<>();

        Session session = factory.openSession();
        Transaction tx = session.beginTransaction();
        if (tx == null) {
            return null;
        }

        try {
            Query query = session.createQuery("SELECT T FROM TimeRecord AS T WHERE T.employee.name = ?1");
            query.setParameter(1, name);
            result = query.getResultList();
            tx.commit();
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
            tx.rollback();
        }
        session.close();
        return result;
    }

}
