package com.example.springboot1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
public class MenuRunner implements CommandLineRunner {

    @Autowired
    private TraineeService traineeService;

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("---------------------------------------------");
        System.out.println("TRAINEE MANAGEMENT SYSTEM");
        System.out.println("---------------------------------------------");
        
        while (true) {
            System.out.println("\nOptions:");
            System.out.println("1. Add Trainee");
            System.out.println("2. List All Trainees");
            System.out.println("3. Find Trainee by ID");
            System.out.println("4. Update Trainee");
            System.out.println("5. Delete Trainee");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            if (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next();
                continue;
            }
            
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("--- Add New Trainee ---");
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Domain: ");
                    String domain = scanner.nextLine();
                    System.out.print("Enter Location: ");
                    String location = scanner.nextLine();
                    
                    Trainee t = new Trainee();
                    t.setTraineeName(name);
                    t.setTraineeDomain(domain);
                    t.setTraineeLocation(location);
                    
                    Trainee saved = traineeService.addTrainee(t);
                    System.out.println("Trainee added successfully with ID: " + saved.getTraineeId());
                    break;
                    
                case 2:
                    System.out.println("--- List All Trainees ---");
                    List<Trainee> all = traineeService.getAllTrainees();
                    if (all.isEmpty()) {
                        System.out.println("No trainees found.");
                    } else {
                        all.forEach(System.out::println);
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter ID: ");
                    if (scanner.hasNextInt()) {
                        int id = scanner.nextInt();
                        if (traineeService.existsById(id)) {
                            System.out.println(traineeService.getTraineeById(id).get());
                        } else {
                            System.out.println("Trainee with ID " + id + " not found.");
                        }
                    } else {
                        System.out.println("Invalid ID format.");
                        scanner.next();
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter ID to update: ");
                    if (scanner.hasNextInt()) {
                        int updateId = scanner.nextInt();
                        scanner.nextLine();

                        if (!traineeService.existsById(updateId)) {
                            System.out.println("Trainee with ID " + updateId + " not found.");
                            break;
                        }
                        
                        System.out.print("Enter New Name: ");
                        String newName = scanner.nextLine();
                        System.out.print("Enter New Domain: ");
                        String newDomain = scanner.nextLine();
                        System.out.print("Enter New Location: ");
                        String newLocation = scanner.nextLine();
                        
                        Trainee updateT = new Trainee();
                        updateT.setTraineeName(newName);
                        updateT.setTraineeDomain(newDomain);
                        updateT.setTraineeLocation(newLocation);
                        
                        try {
                            traineeService.updateTrainee(updateId, updateT);
                            System.out.println("Trainee updated successfully.");
                        } catch (Exception e) {
                            System.out.println("Error updating trainee: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Invalid ID format.");
                        scanner.next();
                    }
                    break;
                case 5:
                    System.out.print("Enter ID to delete: ");
                    if (scanner.hasNextInt()) {
                        int deleteId = scanner.nextInt();
                        if (traineeService.existsById(deleteId)) {
                            try {
                                traineeService.deleteTrainee(deleteId);
                                System.out.println("Trainee deleted.");
                            } catch (Exception e) {
                                System.out.println("Error deleting trainee: " + e.getMessage());
                            }
                        } else {
                            System.out.println("Trainee with ID " + deleteId + " not found.");
                        }
                    } else {
                        System.out.println("Invalid ID format.");
                        scanner.next(); // consume
                    }
                    break;
                case 6:
                    System.out.println("Exiting application...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number between 1 and 7.");
            }
        }
    }
}

