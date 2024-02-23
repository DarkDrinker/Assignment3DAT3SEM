package Week4.Dolphin;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class DolphinDAO<T> {

    private EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig("doplhin");

    public void save(T t) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(t);
        em.getTransaction().commit();
        em.close();
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

    public void delete(T t)
    {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.remove(t);
        em.getTransaction().commit();
        em.close();
    }

    public T findById(int id, Class<T> t) {
        EntityManager em = emf.createEntityManager();
        T foundT = em.find(t, id);
        em.close();
        return foundT;
    }

    public List<Fee> getallPayments(Person p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Fee> fees = em.createQuery("SELECT f FROM Fee f WHERE f.person = :person", Fee.class)
                .setParameter("person", p)
                .getResultList();
        em.getTransaction().commit();
        em.close();
        return fees;
    }

    public List<Note> getAllNotesFromPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        List<Note> notes = em.createQuery("SELECT n FROM Note n WHERE n.person = :person", Note.class)
                .setParameter("person", p)
                .getResultList();
        em.getTransaction().commit();
        em.close();
        return notes;
    }

    public void GetEverythingOnThisPerson(Person p) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery("SELECT n, p.name, pD.age " +
                "FROM Note n             " +
                "JOIN n.person p         " +
                "JOIN p.personDetail pD  " +
                "WHERE n.person = :person", Note.class).setParameter("person", p).getResultList();
        em.getTransaction().commit();
        em.close();
    }
    public void close()
    {
        emf.close();
    }
}
