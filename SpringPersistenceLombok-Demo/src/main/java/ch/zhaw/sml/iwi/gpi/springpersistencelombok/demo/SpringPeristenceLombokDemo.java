package ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo;



import ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo.entity.Project;
import ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo.entity.TimeRecord;
import ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo.repository.TimeRecordRepository;
import java.util.Date;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories(basePackages = "ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo")
public class SpringPeristenceLombokDemo implements CommandLineRunner{
    
    public static void main(String[] args) {
        SpringApplication.run(SpringPeristenceLombokDemo.class, args);
    }

    @Autowired
    private TimeRecordRepository recordRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
    
        Project p = new Project("GPI Vorlesung");
      
        Employee e = new Employee("Peter Heinrich");
        
        TimeRecord tr = new TimeRecord(p, e, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 8), new Date(System.currentTimeMillis()));
        recordRepository.save(tr);
        
        e = new Employee("Björn Scheppler");
        
        tr = new TimeRecord(p, e, new Date(System.currentTimeMillis() - 1000 * 60 * 60 * 8), new Date(System.currentTimeMillis()));
        recordRepository.save(tr);
        
        System.out.println("Dump all Entries:");
        Iterable<TimeRecord> timeRecords = recordRepository.findAll();
        for (TimeRecord em : timeRecords) {
            System.out.println("ch.zhaw.sml.iwi.gpi.jdbc.demo.JDBCDemo.main() " + em.getId() + " " + em.getEmployee().getName() + " " + em.getStart() + " " + em.getEnd());
        }
        
        System.out.println("Dump all Entries of Björn:");
        timeRecords = recordRepository.findAllByName("Björn Scheppler");
        for (TimeRecord em : timeRecords) {
            System.out.println("ch.zhaw.sml.iwi.gpi.jdbc.demo.JDBCDemo.main() " + em.getId() + " " + em.getEmployee().getName() + " " + em.getStart() + " " + em.getEnd());
        }
    } 
}