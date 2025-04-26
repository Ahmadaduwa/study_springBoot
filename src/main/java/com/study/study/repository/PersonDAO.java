package com.study.study.repository;

import com.study.study.entity.Person;

import java.util.List;

public interface PersonDAO {
    //บันทึก object ลงไปเก็บในฐานข้อมูล
    void save(Person person);
    void delete(Integer id);
    Person get(Integer id);
    List<Person> getAll();
    void update(Person person);
}
