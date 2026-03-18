package com.example.springboot1;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TraineeJpaRepository extends JpaRepository<Trainee, Integer> {
    List<Trainee> findByTraineeName(String traineeName);
}

