package com.example.springboot1;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Trainee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int traineeId;
    private String traineeName;
    private String traineeDomain;
    private String traineeLocation;
    
    // Getters and Setters
    public int getTraineeId() { return traineeId; }
    public void setTraineeId(int traineeId) { this.traineeId = traineeId; }
    public String getTraineeName() { return traineeName; }
    public void setTraineeName(String traineeName) { this.traineeName = traineeName; }
    public String getTraineeDomain() { return traineeDomain; }
    public void setTraineeDomain(String traineeDomain) { this.traineeDomain = traineeDomain; }
    public String getTraineeLocation() { return traineeLocation; }
    public void setTraineeLocation(String traineeLocation) { this.traineeLocation = traineeLocation; }

    @Override
    public String toString() {
        return "Trainee [id=" + traineeId + ", name=" + traineeName + ", domain=" + traineeDomain + ", location=" + traineeLocation + "]";
    }
}

