package oracle.model;

import java.util.List;

import javax.annotation.Resource;

import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless(name = "HRFacade", mappedName = "HR_EJB_JPA-Model-HRFacade")
public class HRFacadeBean implements HRFacade, HRFacadeLocal {
    @Resource
    SessionContext sessionContext;
    @PersistenceContext(unitName = "Model")
    private EntityManager em;

    public HRFacadeBean() {
    }

    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public Object queryByRange(String jpqlStmt, int firstResult, int maxResults) {
        Query query = em.createQuery(jpqlStmt);
        if (firstResult > 0) {
            query = query.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            query = query.setMaxResults(maxResults);
        }
        return query.getResultList();
    }

    public <T> T persistEntity(T entity) {
        em.persist(entity);
        return entity;
    }

    public <T> T mergeEntity(T entity) {
        return em.merge(entity);
    }

    public Departments persistDepartments(Departments departments) {
        em.persist(departments);
        return departments;
    }

    public Departments mergeDepartments(Departments departments) {
        return em.merge(departments);
    }

    public void removeDepartments(Departments departments) {
        departments = em.find(Departments.class, departments.getDepartmentId());
        em.remove(departments);
    }

    /** <code>select o from Departments o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Departments> getDepartmentsFindAll() {
        return em.createNamedQuery("Departments.findAll", Departments.class).getResultList();
    }

    public Employees persistEmployees(Employees employees) {
        em.persist(employees);
        return employees;
    }

    public Employees mergeEmployees(Employees employees) {
        return em.merge(employees);
    }

    public void removeEmployees(Employees employees) {
        employees = em.find(Employees.class, employees.getEmployeeId());
        em.remove(employees);
    }

    /** <code>select o from Employees o</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employees> getEmployeesFindAll() {
        return em.createNamedQuery("Employees.findAll", Employees.class).getResultList();
    }


    /** <code>select o from Employees o where o.firstName like :p_name</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employees> getEmployeesFindByName(String p_name) {
        return em.createNamedQuery("Employees.findByName", Employees.class).setParameter("p_name",
                                                                                         p_name).getResultList();
    }

    /** <code>select o from Employees o where o.salary > :p_sal</code> */
    @TransactionAttribute(TransactionAttributeType.NOT_SUPPORTED)
    public List<Employees> getEmployeesFindBySal(Integer p_sal) {
        return em.createNamedQuery("Employees.findBySal", Employees.class).setParameter("p_sal", p_sal).getResultList();
    }
}
