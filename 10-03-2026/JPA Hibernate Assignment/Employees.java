package cg.demo.jpahibernate.activity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
//import jakarta.persistence.Table;
//import jakarta.persistence.Column;

@Entity
//@Table(name="empl") ... to specify custom table name for the entity class
public class Employees {
//	@Column(name="emp_id")... to specify custom column name for the field in the database table
//	@GeneratedValue .... single value sequence created .. automatic id generated for primary key entities
//	@GeneratedValue(strategy=GenerationType.IDENTITY) // auto incremented id generated for all entities with new sequence values starting with 1
//	@ElementCollection .... collection of values for a single entity apply on wrapper class like List<String> or 
//	Set<String> or Map<String, String> etc only hence separate table created for the collection and mapped with 
//	the main entity table using foreign key relationship

	
//	@Embeddable .... to create reusable component class for common attributes of multiple entities and use it
//	as a field in those entities hence no separate table created for the component class and its fields are 
//	mapped to the main entity table as columns

	
//	@email .... to validate email format for a String field
//	@NotNull ... to validate that a field value should not be null
//	@Notblank ... to validate that a String field value should not be null or empty
//	@Size(min=2, max=20) ... to validate that a String field value should have
//	length between 2 and 20
//	@Min(1000) ... to validate that a numeric field value should be minimum 1000
//  @Max(100000) ... to validate that a numeric field value should be maximum 100000
//  @Past ... to validate that a Date field value should be in the past
//  @Future ... to validate that a Date field value should be in the future
//  @Pattern(regexp="^[A-Za-z]+$") ... to validate that a String field value should match the given regular expression pattern
//	@Transient ... to mark a field as non-persistent and not mapped to any database column
//	@Password ... to encrypt a String field value before storing it in the database and decrypt it when retrieving it from the database
//	
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;

	    private String ename;
	    private double salary;
	    private String dept;

	    @ElementCollection
	    private List<Long> mobileNumbers = new ArrayList<>();

	    public Employees() {}

	    public Employees(String ename, double salary, String dept, List<Long> mobileNumbers) {
	        this.ename = ename;
	        this.salary = salary;
	        this.dept = dept;
	        this.mobileNumbers = mobileNumbers;
	    }

	    public int getId() {
	        return id;
	    }

	    public String getEname() {
	        return ename;
	    }

	    public void setEname(String ename) {
	        this.ename = ename;
	    }

	    public double getSalary() {
	        return salary;
	    }

	    public void setSalary(double salary) {
	        this.salary = salary;
	    }

	    public String getDept() {
	        return dept;
	    }

	    public void setDept(String dept) {
	        this.dept = dept;
	    }

	    public List<Long> getMobileNumbers() {
	        return mobileNumbers;
	    }

	    public void setMobileNumbers(List<Long> mobileNumbers) {
	        this.mobileNumbers = mobileNumbers;
	    }

	    @Override
	    public String toString() {
	        return "Employees [id=" + id +
	                ", ename=" + ename +
	                ", salary=" + salary +
	                ", dept=" + dept +
	                ", mobiles=" + mobileNumbers + "]";
	    }
	}