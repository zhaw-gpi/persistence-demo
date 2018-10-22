package ch.zhaw.sml.iwi.gpi.springpersistencelombok.demo.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;


@Data
@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private String name;

    public Employee() {
    }

    public Employee(String name) {
        this.name = name;
    }

}
