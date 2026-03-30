package com.example.demo;

//import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.example.demo.entities.Trainee;
import com.example.demo.repo.ITraineeRepository;

@SpringBootApplication
public class TraineeApiApplication {
//	@Autowired
//	ITraineeRepository iTraineeRepository;
	public static void main(String[] args) {
		SpringApplication.run(TraineeApiApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//
//		Scanner sc = new Scanner(System.in);
//		int choice;
//
//		do {
//			System.out.println("\n========== Trainee Management Menu ==========");
//			System.out.println("1. Insert Trainee");
//			System.out.println("2. Fetch All Trainees");
//			System.out.println("3. Fetch Trainee by ID");
//			System.out.println("4. Update Trainee by ID");
//			System.out.println("5. Delete Trainee by ID");
//			System.out.println("6. Find Trainees by Name");
//			System.out.println("7. Exit");
//			System.out.print("Enter your choice: ");
//			choice = sc.nextInt();
//			sc.nextLine(); 
//
//			switch (choice) {
//
//			case 1:
//				// -------------- Insert Trainee --------------
//				Trainee t1 = new Trainee();
//				System.out.print("Enter Name: ");
//				t1.setTraineeName(sc.nextLine());
//				System.out.print("Enter Domain: ");
//				t1.setTraineeDomain(sc.nextLine());
//				System.out.print("Enter Location: ");
//				t1.setTraineeLocation(sc.nextLine());
//				iTraineeRepository.save(t1);
//				System.out.println("Trainee inserted with ID: " + t1.getTraineeId());
//				break;
//
//			case 2:
//				// -------------- Fetch All Trainees --------------
//				List<Trainee> list = iTraineeRepository.findAll();
//				if (list.isEmpty()) {
//					System.out.println("No trainees found.");
//				} else {
//					list.forEach(t -> System.out.println(t));
//				}
//				break;
//
//			case 3:
//				// -------------- Fetch Trainee by ID --------------
//				System.out.print("Enter Trainee ID: ");
//				int fetchId = sc.nextInt();
//				Trainee found = iTraineeRepository.findById(fetchId).orElse(null);
//				if (found != null) {
//					System.out.println(found);
//				} else {
//					System.out.println("Trainee not found with ID: " + fetchId);
//				}
//				break;
//
//			case 4:
//				// -------------- Update Trainee --------------
//				System.out.print("Enter Trainee ID to update: ");
//				int updateId = sc.nextInt();
//				sc.nextLine();
//				Trainee existing = iTraineeRepository.findById(updateId).orElse(null);
//				if (existing != null) {
//					System.out.print("Enter new Name: ");
//					existing.setTraineeName(sc.nextLine());
//					System.out.print("Enter new Domain: ");
//					existing.setTraineeDomain(sc.nextLine());
//					System.out.print("Enter new Location: ");
//					existing.setTraineeLocation(sc.nextLine());
//					iTraineeRepository.save(existing);
//					System.out.println("Trainee updated successfully.");
//				} else {
//					System.out.println("Trainee not found with ID: " + updateId);
//				}
//				break;
//
//			case 5:
//				// -------------- Delete Trainee by ID --------------
//				System.out.print("Enter Trainee ID to delete: ");
//				int deleteId = sc.nextInt();
//				if (iTraineeRepository.existsById(deleteId)) {
//					iTraineeRepository.deleteById(deleteId);
//					System.out.println("Trainee deleted successfully.");
//				} else {
//					System.out.println("Trainee not found with ID: " + deleteId);
//				}
//				break;
//				
//			case 6:
//				// -------------- Find Trainees by Name using optional --------------
//				System.out.print("Enter Trainee Name to search: ");
//				String searchName = sc.nextLine();
//				Optional<List<Trainee>> optionalTrainees = Optional.ofNullable(iTraineeRepository.findByTraineeName(searchName));
//				if (optionalTrainees.isPresent() && !optionalTrainees.get().isEmpty()) {
//					optionalTrainees.get().forEach(t -> System.out.println(t));
//				} else {
//					System.out.println("No trainees found with name: " + searchName);
//				}
//				break;
//				
//			case 7:
//				System.out.println("Exiting... Goodbye!");
//				break;
//
//			default:
//				System.out.println("Invalid choice. Please try again.");
//			}
//
//		} while (choice != 7);
//
//		sc.close();
//
//	}
}
