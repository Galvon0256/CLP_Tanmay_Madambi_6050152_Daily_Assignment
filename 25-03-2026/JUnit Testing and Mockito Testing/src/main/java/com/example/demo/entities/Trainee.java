package com.example.demo.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "trainee")
public class Trainee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer traineeId;

    private String traineeName;
    private String traineeDomain;
    private String traineeLocation;

    public Integer getTraineeId() {
		return traineeId;
	}

	public void setTraineeId(Integer traineeId) {
		this.traineeId = traineeId;
	}

	public String getTraineeName() { return traineeName; }
    public void setTraineeName(String traineeName) { this.traineeName = traineeName; }

    public String getTraineeDomain() { return traineeDomain; }
    public void setTraineeDomain(String traineeDomain) { this.traineeDomain = traineeDomain; }

    public String getTraineeLocation() { return traineeLocation; }
    public void setTraineeLocation(String traineeLocation) { this.traineeLocation = traineeLocation; }
    
    @Override
    public String toString() {
        return "Trainee [id=" + traineeId + 
               ", name=" + traineeName + 
               ", domain=" + traineeDomain + 
               ", location=" + traineeLocation + "]";
    }
}
