package Week6.HotelAssignment.DAO;

import Week6.HotelAssignment.config.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.TypedQuery;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class DAO<T, S> implements IDAO<T, S>{
    Class<T> entityClass;
    public static EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
    protected Map<Integer,T> entityMap;

    public DAO(EntityManagerFactory emf) {
        this.emf = emf;
        entityMap = new HashMap<>();
    }

    public T create(T t) {
        try(var em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(t);
            em.getTransaction().commit();
        }
        return t;
    }

    public void delete(S id) {
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            T toRemove = em.find(entityClass,id);
            em.remove(toRemove);
            em.getTransaction().commit();
        }
    }

    public T update(T t, S id) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            T toMerge = em.find(entityClass, id);
            if (toMerge != null) {
                T toReturn = em.merge(t);
                em.getTransaction().commit();
                return toReturn;
            }
            return null;
        }
    }
    public List<T> getAll() {
        try(EntityManager em = emf.createEntityManager()) {
            TypedQuery<T> query = em.createQuery("SELECT e from "+ entityClass.getSimpleName()+" e", entityClass);
            List<T> queryList = query.getResultList();
            return queryList;
        }
    }
    public T getById(S id) {
        try(EntityManager em = emf.createEntityManager()) {
            return em.find(entityClass,id);
        }
    }

}

