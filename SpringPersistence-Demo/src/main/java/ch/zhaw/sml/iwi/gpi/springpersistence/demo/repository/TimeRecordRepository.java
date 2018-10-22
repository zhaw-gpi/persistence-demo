package ch.zhaw.sml.iwi.gpi.springpersistence.demo.repository;

import ch.zhaw.sml.iwi.gpi.springpersistence.demo.entity.TimeRecord;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface TimeRecordRepository extends CrudRepository<TimeRecord,Long> {
    @Query("SELECT T FROM TimeRecord AS T WHERE T.employee.name = ?1")
    public Iterable<TimeRecord> findAllByName(String name);
}
