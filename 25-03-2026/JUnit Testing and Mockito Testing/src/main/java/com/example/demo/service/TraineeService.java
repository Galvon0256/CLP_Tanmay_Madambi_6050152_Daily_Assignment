package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Trainee;
import com.example.demo.repo.ITraineeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {

    @Autowired
    private ITraineeRepository traineeRepo;

    public List<Trainee> getAllTrainees() {
        return traineeRepo.findAll();
    }

    public Trainee getTraineeById(int id) {
        Optional<Trainee> trainee = traineeRepo.findById(id);
        return trainee.orElse(null);
    }
    
    public List<Trainee> findByTraineeName(String name) {
		return traineeRepo.findByTraineeName(name);
	}
    
    public Trainee insertTrainee(Trainee trainee) {
        return traineeRepo.save(trainee);
    }

    public Trainee updateTrainee(int id, Trainee updatedTrainee) {
        Optional<Trainee> existing = traineeRepo.findById(id);
        if (existing.isPresent()) {
            Trainee trainee = existing.get();
            trainee.setTraineeName(updatedTrainee.getTraineeName());
            trainee.setTraineeDomain(updatedTrainee.getTraineeDomain());
            trainee.setTraineeLocation(updatedTrainee.getTraineeLocation());
            return traineeRepo.save(trainee);
        }
        return null;
    }

    public boolean deleteTrainee(int id) {
        if (traineeRepo.existsById(id)) {
            traineeRepo.deleteById(id);
            return true;
        }
        return false;
    }
}