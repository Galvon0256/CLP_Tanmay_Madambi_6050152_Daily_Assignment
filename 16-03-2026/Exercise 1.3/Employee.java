package org.example.springcore.beans;

public class Employee {
    private int empAge;
    private int empId;
    private String empName;
    private double empSalary;
    private SBU businessUnit;

    public Employee() {
    }
    public Employee(int empAge, int empId, String empName, double empSalary, SBU businessUnit) {
        this.empAge = empAge;
        this.empId = empId;
        this.empName = empName;
        this.empSalary = empSalary;
        this.businessUnit = businessUnit;
    }

    public int getEmpAge() {
        return empAge;
    }

    public void setEmpAge(int empAge) {
        this.empAge = empAge;
    }

    public int getEmpId() {
        return empId;
    }

    public void setEmpId(int empId) {
        this.empId = empId;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public double getEmpSalary() {
        return empSalary;
    }

    public void setEmpSalary(double empSalary) {
        this.empSalary = empSalary;
    }

    public SBU getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(SBU businessUnit) {
        this.businessUnit = businessUnit;
    }

    public SBU getSBUDetails() {
        return businessUnit;
    }

    @Override
    public String toString() {
        String sbuInfo = businessUnit == null
                ? "NA"
                : businessUnit.getSbuCode() + " - " + businessUnit.getSbuName();

        return "Employee [empAge=" + empAge
                + ", empId=" + empId
                + ", empName=" + empName
                + ", empSalary=" + empSalary
                + ", sbu=" + sbuInfo
                + "]";
    }
}
