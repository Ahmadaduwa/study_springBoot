package com.study.study.controllers;

import com.study.study.entity.Person;
import com.study.study.repository.PersonDAO;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CrudController {

    @Bean //โค้ดนี้จะรันเมื่อเริ่มโปรแกรม
    public CommandLineRunner commandLineRunner(PersonDAO dao){
        return runner-> {

            System.out.println("Start");
            getAllData(dao);

        } ;
    }

    public void saveData (PersonDAO dao){
        Person obj1 = new Person("โจโจ้", "โจโจ้");
        dao.save(obj1);
        System.out.println("Insert Complete");
    }

    public void deleteData(PersonDAO dao){
        int id = 1;
        dao.delete(id);
        System.out.println("Delete Complete");
    }

    public void getData(PersonDAO dao){
        int id = 2;
        Person person = dao.get(id);
        System.out.println(person);
        System.out.println("get Complete");
    }

    public void getAllData(PersonDAO dao){

        List<Person> personList = dao.getAll();
        for (Person person : personList) {
            System.out.println(person);
        }

        System.out.println("getAll Complete");
    }

    public void updateData(PersonDAO dao){
        int id = 3;
        Person person = dao.get(id);
        person.setfName("นายอาหมัด");
        person.setlName("ดาโอะ");
        dao.update(person);
        System.out.println("Update Complete");
    }
}
