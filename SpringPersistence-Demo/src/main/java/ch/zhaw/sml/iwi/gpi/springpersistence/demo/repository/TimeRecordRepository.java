package ch.zhaw.sml.iwi.gpi.springpersistence.demo.repository;

import ch.zhaw.sml.iwi.gpi.springpersistence.demo.entity.Employee;
import ch.zhaw.sml.iwi.gpi.springpersistence.demo.entity.TimeRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface TimeRecordRepository extends JpaRepository<TimeRecord,Long> {
    @Query("SELECT T FROM TimeRecord AS T WHERE T.employee.name = ?1")
    public Iterable<TimeRecord> findAllByName(String name);
    
    
    public Iterable<TimeRecord> findAllByEmployee(Employee employee);
}
