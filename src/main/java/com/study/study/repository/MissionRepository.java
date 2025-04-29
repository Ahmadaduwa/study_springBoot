package com.study.study.repository;

import com.study.study.models.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Integer> {

}