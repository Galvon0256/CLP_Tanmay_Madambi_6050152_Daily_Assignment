package com.example.springboot1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class TraineeService {
    @Autowired
    private TraineeJpaRepository traineeRepo;

    public Trainee addTrainee(Trainee trainee) {
        return traineeRepo.save(trainee);
    }
    
    public List<Trainee> getAllTrainees() {
        return traineeRepo.findAll();
    }
    
    public Optional<Trainee> getTraineeById(int id) {
        return traineeRepo.findById(id);
    }
    
    public boolean existsById(int id) {
        return traineeRepo.existsById(id);
    }

    public void deleteTrainee(int id) {
        traineeRepo.deleteById(id);
    }
    
    public Trainee updateTrainee(int id, Trainee traineeDetails) {
        Trainee trainee = traineeRepo.findById(id).orElseThrow(() -> new RuntimeException("Trainee not found"));
        trainee.setTraineeName(traineeDetails.getTraineeName());
        trainee.setTraineeDomain(traineeDetails.getTraineeDomain());
        trainee.setTraineeLocation(traineeDetails.getTraineeLocation());
        return traineeRepo.save(trainee);
    }
}
