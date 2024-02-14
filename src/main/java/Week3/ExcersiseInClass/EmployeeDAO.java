package Week3.ExcersiseInClass;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class EmployeeDAO
{

    private static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Employee save(Employee employee)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
        return employee;
    }

    public Employee findById(int id)
    {
        EntityManager em = emf.createEntityManager();
        Employee foundEmployee = em.find(Employee.class, id);
        em.close();
        return foundEmployee;
    }

    public Employee update(Employee employee)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Employee updatedUnicorn = em.merge(employee);
        em.getTransaction().commit();
        em.close();
        return updatedUnicorn;
    }

    public void delete(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Employee employee = findById(id);
        if (employee != null)
        {
            em.remove(employee);
        }
        em.getTransaction().commit();
        em.close();
    }

    public static List<Employee> number1() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT u FROM Employee u", Employee.class);
        return query.getResultList();
    }

    public static List<Employee> number2() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT u FROM Employee u where u.salary > 1000", Employee.class);
        return query.getResultList();
    }
    public static List<Employee> number3() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT u FROM Employee u where u.department = 'Finance'", Employee.class);
        return query.getResultList();
    }
    public static List<Employee> number4() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT u FROM Employee u where u.firstName LIKE 'A%'", Employee.class);
        return query.getResultList();
    }
    public static void number5(int id){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Employee u SET u.salary = u.salary + 1000000 WHERE u.id = :name ")
                .setParameter("name", id)
                .executeUpdate();
        em.getTransaction().commit();
    }
    public static void number6(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("UPDATE Employee u SET u.department = 'CEO' WHERE u.id = ?1 ")
                .setParameter(1, id)
                .executeUpdate();
        em.getTransaction().commit();
    }

    public static int number7(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT AVG(u.salary) FROM Employee u", Employee.class);
        return query.getFirstResult();
    }

    public static int number8(){
        EntityManager em = emf.createEntityManager();
        TypedQuery<Employee> query = em.createQuery("SELECT SUM(u.salary) FROM Employee u", Employee.class);
        return query.getFirstResult();
    }


    public static void close()
    {
        emf.close();
    }
}