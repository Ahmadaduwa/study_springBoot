package com.study.study.services;

import com.study.study.entity.Person;
import java.util.List;

public interface PersonService {
    Person findById(Integer id);
    List<Person> findAll();
    Person create(Person person);
    void deleteById(Integer id);
    Person update(Person person);
    Person partialUpdate(Integer id, Person person);
}
