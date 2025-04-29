package com.study.study.repository;

import com.study.study.models.Auth.Users;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

    @EntityGraph(attributePaths = {"roles"})
    Optional<Users> findByUsername(String username);
}
