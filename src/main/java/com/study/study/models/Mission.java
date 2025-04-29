package com.study.study.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "mission")
public class Mission {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "mission_sequence"
    )
    @SequenceGenerator(
            name = "mission_sequence",
            sequenceName = "mission_sequence",
            allocationSize = 1
    )
    @Column(name = "id", updatable = false, nullable = false)
    private Integer id;

    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;

    @Column(name = "period", nullable = false, columnDefinition = "TEXT")
    private String period;

    @ManyToMany(mappedBy = "mission")
    private List<Employee> employee;

    /////////////////
    public Mission() {
    }

    public Mission(String name, String period) {
        this.name = name;
        this.period = period;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public List<Employee> getEmployee() {
        return employee;
    }

    public void setEmployee(List<Employee> employee) {
        this.employee = employee;
    }
}
