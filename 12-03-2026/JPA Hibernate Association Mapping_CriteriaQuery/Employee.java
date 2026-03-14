package cg.demo.associationcriteriamapping;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private double salary;

    @ElementCollection
    private List<Long> mobileNumbers = new ArrayList<>();

    @ManyToOne(cascade = CascadeType.ALL)
    private Department department;

    public int getId() { return id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getSalary() { return salary; }
    public void setSalary(double salary) { this.salary = salary; }

    public List<Long> getMobileNumbers() { return mobileNumbers; }
    public void setMobileNumbers(List<Long> mobileNumbers) { this.mobileNumbers = mobileNumbers; }

    public Department getDepartment() { return department; }
    public void setDepartment(Department department) { this.department = department; }

    @Override
    public String toString() {
        return "Employee [id=" + id + ", name=" + name + ", salary=" + salary +
               ", mobiles=" + mobileNumbers + "]";
    }
}