package com.study.study.services;

import com.study.study.entity.Person;
import com.study.study.exception.PersonNotFoundException;
import com.study.study.repository.PersonJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceAction implements PersonService {

    private final PersonJpaRepository personJpaRepository;

    @Autowired
    public PersonServiceAction(PersonJpaRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Person findById(Integer id) {
        return personJpaRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    @Override
    public List<Person> findAll() {
        return personJpaRepository.findAll();
    }

    @Override
    public Person create(Person person) {
        person.setId(0);
        return personJpaRepository.save(person);
    }

    @Override
    public void deleteById(Integer id) {
        if (!personJpaRepository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }
        personJpaRepository.deleteById(id);
    }

    @Override
    public Person update(Person person) {
        // ตรวจสอบว่ามี Person นี้ในฐานข้อมูลหรือไม่ boolean
        if (!personJpaRepository.existsById(person.getId())) {
            throw new PersonNotFoundException(person.getId());
        }
        return personJpaRepository.save(person);
    }

    @Override
    public Person partialUpdate(Integer id, Person person) {
        Person existingPerson = personJpaRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));

        if (person.getfName() != null) {
            existingPerson.setfName(person.getfName());
        }
        if (person.getlName() != null) {
            existingPerson.setlName(person.getlName());
        }

        return personJpaRepository.save(existingPerson);
    }
}
