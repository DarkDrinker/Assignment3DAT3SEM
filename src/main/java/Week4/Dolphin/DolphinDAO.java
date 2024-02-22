package Week4.Dolphin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

public class DolphinDAO {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    public Person save(Person employee) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
        return employee;
    }

    public Person update(Person employee)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person updatedUnicorn = em.merge(employee);
        em.getTransaction().commit();
        em.close();
        return updatedUnicorn;
    }

    public void delete(int id)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Person employee = findById(id);
        if (employee != null)
        {
            em.remove(employee);
        }
        em.getTransaction().commit();
        em.close();
    }
}
