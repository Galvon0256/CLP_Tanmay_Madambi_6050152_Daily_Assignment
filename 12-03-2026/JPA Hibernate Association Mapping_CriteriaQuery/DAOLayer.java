package cg.demo.associationcriteriamapping;

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
    
    public List<Employee> fetchAllEmployees() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
        
        Root<Employee> root = cq.from(Employee.class);
        
        cq.select(root);
        
        TypedQuery<Employee> query = em.createQuery(cq);
        
        return query.getResultList();
    }
    
    public List<Object[]> fetchAllEmployeesWithDept() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Employee> e = cq.from(Employee.class);
        Join<Employee, Department> d = e.join("department", JoinType.INNER);

        cq.multiselect(
            e.get("id"), e.get("name"), e.get("salary"), d.get("deptName"), d.get("managerName")
        );

        TypedQuery<Object[]> query = em.createQuery(cq);

        return query.getResultList();
    }

    
    public List<Object[]> fetchEmpCountByDept() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Employee> e = cq.from(Employee.class);
        Join<Employee, Department> d = e.join("department", JoinType.INNER);

        cq.multiselect(
            d.get("deptName"), cb.count(e)
        );

        cq.groupBy(d.get("deptName"));

        TypedQuery<Object[]> query = em.createQuery(cq);

        return query.getResultList();
    }
    
    public List<Employee> fetchEmployeesByDeptName(String deptName) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);

        Root<Employee> e = cq.from(Employee.class);
        Join<Employee, Department> d = e.join("department", JoinType.INNER);

        cq.select(e);
        cq.where(cb.equal(d.get("deptName"), deptName));

        TypedQuery<Employee> query = em.createQuery(cq);

        return query.getResultList();
    }
    
    public List<Employee> fetchEmployeeGreaterThanSalary(double salary) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Employee> cq = cb.createQuery(Employee.class);
		Root<Employee> root = cq.from(Employee.class);
		
		Predicate predicate = cb.gt(root.get("salary"), salary);
		
		cq.select(root).where(predicate);
		return em.createQuery(cq).getResultList();
	}
    
    public Object[] fetchEmpAndDeptByMobile(long mobile) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Object[]> cq = cb.createQuery(Object[].class);

        Root<Employee> e = cq.from(Employee.class);
        Join<Employee, Department> d = e.join("department", JoinType.INNER);
        Join<Employee, Long> m = e.join("mobileNumbers", JoinType.INNER);

        cq.multiselect(
            e.get("id"),
            e.get("name"),
            d
        );

        cq.where(cb.equal(m, mobile));

        TypedQuery<Object[]> query = em.createQuery(cq);
        List<Object[]> list = query.getResultList();

        return list.isEmpty() ? null : list.get(0);
    }

    public void close() {
        em.close();
        emf.close();
    }
}