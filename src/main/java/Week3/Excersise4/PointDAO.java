package Week3.Excersise4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PointDAO {
    EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

    // Store 1000 Point objects in the database:
    public void fillDB() {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        for (int i = 0; i < 1000; i++) {
            Point p = new Point(i, i);
            em.persist(p);
        }
        em.getTransaction().commit();
    }

    // Find the number of Point objects in the database:
    public Long NumberOfObjectsInDB() {
        EntityManager em = emf.createEntityManager();
        Query q1 = em.createQuery("SELECT COUNT(p) FROM Point p");
        return (Long) q1.getSingleResult();
    }

    // Find the average X value:
    public Object AverageX() {
        EntityManager em = emf.createEntityManager();
        Query q2 = em.createQuery("SELECT AVG(p.x) FROM Point p");
        return q2.getSingleResult();
    }

    // Retrieve all the Point objects from the database:
    public List<Point> ListOfPoints() {
        EntityManager em = emf.createEntityManager();
        TypedQuery<Point> query = em.createQuery("SELECT p FROM Point p", Point.class);
        List<Point> results = query.getResultList();
        em.close();
        return results;
    }
    // Close the database connection:
    public void close()
    {
        emf.close();
    }
}
