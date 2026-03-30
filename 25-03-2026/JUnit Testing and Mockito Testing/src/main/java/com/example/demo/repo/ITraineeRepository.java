package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.Trainee;

import java.util.List;

@Repository
public interface ITraineeRepository extends JpaRepository<Trainee, Integer> {
	List<Trainee> findByTraineeName(String name);
}
