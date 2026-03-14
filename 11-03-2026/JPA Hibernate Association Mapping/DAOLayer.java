package cg.demo.associationmapping;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class DAOLayer {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("JPA-PU");
    private EntityManager em = emf.createEntityManager();

    public void insertEmployee(Employee emp) {
        em.getTransaction().begin();
        em.persist(emp);
        em.getTransaction().commit();
    }


    public List<Object[]> fetchAllEmployeesWithDept() {
        return em.createQuery(
                "SELECT e.id, e.name, e.salary, d.deptName, d.managerName FROM Employee e JOIN e.department d", Object[].class
        ).getResultList();
    }


    public List<Object[]> fetchEmpCountByDept() {
        return em.createQuery(
                "SELECT d.deptName, COUNT(e) FROM Employee e JOIN e.department d GROUP BY d.deptName",
                Object[].class).getResultList();
    }


    public List<Employee> fetchEmployeesByDeptName(String deptName) {
        return em.createQuery(
                "SELECT e FROM Employee e JOIN e.department d WHERE d.deptName = :deptName",
                Employee.class
        )
        .setParameter("deptName", deptName)
        .getResultList();
    }

    public Object[] fetchEmpAndDeptByMobile(long mobile) {
        List<Object[]> list = em.createQuery(
                "SELECT e.id, e.name, d FROM Employee e JOIN e.department d JOIN e.mobileNumbers m WHERE m = :mob",
                Object[].class
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