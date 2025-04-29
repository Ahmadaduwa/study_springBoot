package com.study.study.services;

import com.study.study.models.Person;
import com.study.study.exception.PersonNotFoundException;
import com.study.study.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personJpaRepository;

    @Autowired
    public PersonService(PersonRepository personJpaRepository) {
        this.personJpaRepository = personJpaRepository;
    }

    public Person findById(Integer id) {
        return personJpaRepository.findById(id)
                .orElseThrow(() -> new PersonNotFoundException(id));
    }

    public List<Person> findAll() {
        return personJpaRepository.findAll();
    }

    public Person create(Person person) {
        person.setId(0);
        return personJpaRepository.save(person);
    }

    public void deleteById(Integer id) {
        if (!personJpaRepository.existsById(id)) {
            throw new PersonNotFoundException(id);
        }
        personJpaRepository.deleteById(id);
    }

    public Person update(Person person) {
        // ตรวจสอบว่ามี Person นี้ในฐานข้อมูลหรือไม่ boolean
        if (!personJpaRepository.existsById(person.getId())) {
            throw new PersonNotFoundException(person.getId());
        }
        return personJpaRepository.save(person);
    }

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
