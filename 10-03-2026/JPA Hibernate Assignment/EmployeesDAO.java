package cg.demo.jpahibernate.activity;

import jakarta.persistence.*;
import java.util.*;

public class EmployeesDAO {

    private EntityManagerFactory emf =
            Persistence.createEntityManagerFactory("JPA-PU");

    private EntityManager em = emf.createEntityManager();

    // INSERT
    public void insertEmployee(Employees emp) {

        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();
    }
    //FIND BY ID
    public Employees fetchById(int id) {

        Employees emp = em.find(Employees.class, id);

        return emp;
    }
    // FETCH ALL
    public List<Employees> fetchAll() {

        return em.createQuery(
                "SELECT e FROM Employees e",
                Employees.class
        ).getResultList();
    }

    // UPDATE SALARY
    public void updateSalary(int id, double salary) {

        Employees emp = em.find(Employees.class, id);

        if (emp != null) {
            em.getTransaction().begin();
            emp.setSalary(salary);
            em.getTransaction().commit();
        }
    }

    // DELETE
    public void deleteEmployee(int id) {

        Employees emp = em.find(Employees.class, id);

        if (emp != null) {
            em.getTransaction().begin();
            em.remove(emp);
            em.getTransaction().commit();
        }
    }

    // COUNT EMPLOYEES IN EACH DEPT
    public List<Object[]> countEmployeesByDept() {

        return em.createQuery(
                "SELECT e.dept, COUNT(e) FROM Employees e GROUP BY e.dept",
                Object[].class
        ).getResultList();
    }

    // FETCH EMPLOYEES WITH PARTICULAR SALARY
    public List<Employees> fetchBySalary(double salary) {

        return em.createQuery(
                "SELECT e FROM Employees e WHERE e.salary = :sal",
                Employees.class
        )
        .setParameter("sal", salary)
        .getResultList();
    }

    // FETCH EMPLOYEE BY MOBILE NUMBER
    public Employees fetchByMobile(long mobile) {

        List<Employees> list = em.createQuery(
                "SELECT e FROM Employees e JOIN e.mobileNumbers m WHERE m = :mob",
                Employees.class
        )
        .setParameter("mob", mobile)
        .getResultList();

        return list.isEmpty() ? null : list.get(0);
    }

    public void close() {
        em.close();
        emf.close();
    }
}