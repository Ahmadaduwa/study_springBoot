package com.study.study.repository;

import com.study.study.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonJpaRepository extends JpaRepository<Person, Integer> {
}
