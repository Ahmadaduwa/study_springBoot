package com.study.study.controllers;

import com.study.study.models.Person;
import com.study.study.services.PersonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/person")
public class ApiController {

    private final PersonService personService;

    public ApiController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/{id}")
    public Person getUser(@PathVariable Integer id) {
        return personService.findById(id);
    }

    @GetMapping
    public List<Person> getAllUsers() {
        return personService.findAll();
    }

    @PostMapping
    public Person addUser(@RequestBody Person person) {
        return personService.create(person);
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Integer id) {
        personService.deleteById(id);
        return "Deleted user with ID: " + id;
    }

    @PutMapping
    public Person putUser(@RequestBody Person person) {
        return personService.update(person);
    }

    @PatchMapping("/{id}")
    public Person patchUser(@PathVariable Integer id, @RequestBody Person person) {
        return personService.partialUpdate(id, person);
    }
}
