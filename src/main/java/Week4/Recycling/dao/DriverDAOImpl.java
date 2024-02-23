package Week4.Recycling.dao;

import Week4.Recycling.model.Driver;
import Week4.Recycling.model.WasteTruck;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

public class DriverDAOImpl implements IDriverDAO {
    private EntityManagerFactory emf;

    public DriverDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void saveDriver(String name, String surname, BigDecimal salary) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Driver driver = new Driver(name, surname, salary);
        em.persist(driver);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public Driver getDriverById(String id) {
        EntityManager em = emf.createEntityManager();
        return em.find(Driver.class, id);
    }

    @Override
    public Driver updateDriver(Driver driver) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Driver updatedDriver = em.merge(driver);
        em.getTransaction().commit();
        em.close();
        return updatedDriver;
    }

    @Override
    public void deleteDriver(String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Driver driver = em.find(Driver.class, id);
        if (driver != null) {
            em.remove(driver);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<Driver> findAllDriversEmployedAtTheSameYear(String year) {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT d FROM Driver d WHERE EXTRACT(YEAR FROM d.employment_date) = :year", Driver.class)
                .setParameter("year", Integer.parseInt(year))
                .getResultList();
    }

    @Override
    public List<Driver> fetchAllDriversWithSalaryGreaterThan10000() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT d FROM Driver d WHERE d.salary > 10000", Driver.class)
                .getResultList();
    }

    @Override
    public double fetchHighestSalary() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT MAX(d.salary) FROM Driver d", Double.class)
                .getSingleResult();
    }

    @Override
    public List<String> fetchFirstNameOfAllDrivers() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT d.name FROM Driver d", String.class)
                .getResultList();
    }

    @Override
    public long calculateNumberOfDrivers() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT COUNT(d) FROM Driver d", Long.class)
                .getSingleResult();
    }

    @Override
    public Driver fetchDriverWithHighestSalary() {
        EntityManager em = emf.createEntityManager();
        List<Driver> drivers = em.createQuery("SELECT d FROM Driver d ORDER BY d.salary DESC", Driver.class)
                .setMaxResults(1)
                .getResultList();
        return drivers.isEmpty() ? null : drivers.get(0);
    }
}