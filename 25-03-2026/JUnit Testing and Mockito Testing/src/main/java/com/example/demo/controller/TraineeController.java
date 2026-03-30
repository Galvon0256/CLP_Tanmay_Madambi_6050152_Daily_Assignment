package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.entities.Trainee;
import com.example.demo.service.TraineeService;

@RestController
@RequestMapping("/trainees")
public class TraineeController {

    @Autowired
    private TraineeService service;

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Trainee>> fetchAllTrainee() {
    	List<Trainee> t = service.getAllTrainees();
        return ResponseEntity.ok(t);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Trainee> fetchTraineeByID(@PathVariable int id) {
        Trainee t = service.getTraineeById(id);
        return (t != null) ? ResponseEntity.ok(t) : ResponseEntity.notFound().build();
    }

    // GET BY NAME
    @GetMapping("/byName")
    public ResponseEntity<List<Trainee>> fetchTraineeByName(@RequestParam String name) {
        List<Trainee> list = service.findByTraineeName(name);
        return list.isEmpty() ? ResponseEntity.notFound().build() : ResponseEntity.ok(list);
    }

    // INSERT
    @PostMapping
    public ResponseEntity<Trainee> insertTrainee(@RequestBody Trainee t) {
    	Trainee created = service.insertTrainee(t);
        return ResponseEntity.status(201).body(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Trainee> updateTrainee(@PathVariable int id, @RequestBody Trainee t) {
        Trainee updated = service.updateTrainee(id, t);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteTraineeWithMessage(@PathVariable int id) {
        boolean b = service.deleteTrainee(id);
        return b ? ResponseEntity.ok("Trainee with ID " + id + " has been deleted.") : ResponseEntity.status(404).body("Trainee with ID " + id + " not found.");
    }
}