package Week4.Recycling.dao;

import Week4.Recycling.model.Driver;
import Week4.Recycling.model.WasteTruck;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;


public class WasteTruckDAOImpl implements IWasteTruckDAO {
    private EntityManagerFactory emf;

    public WasteTruckDAOImpl(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public void saveWasteTruck(String brand, String registrationNumber, int capacity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        WasteTruck wasteTruck = new WasteTruck(brand,capacity, registrationNumber);
        em.persist(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public WasteTruck getWasteTruckById(int id) {
        EntityManager em = emf.createEntityManager();
        return em.find(WasteTruck.class, id);
    }

    @Override
    public void setWasteTruckAvailable(WasteTruck wasteTruck, WasteTruck.AvailabilityStatus availabilityStatus) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        wasteTruck.setAvailabilityStatus(availabilityStatus);
        em.merge(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void deleteWasteTruck(int id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        WasteTruck wasteTruck = em.find(WasteTruck.class, id);
        if (wasteTruck != null) {
            em.remove(wasteTruck);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        wasteTruck.getDriver().add(driver);
        em.merge(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Driver driverToRemove = em.find(Driver.class, id);
        if (driverToRemove != null) {
            wasteTruck.getDriver().remove(driverToRemove);
        }
        em.merge(wasteTruck);
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<WasteTruck> getAllAvailableTrucks() {
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT wt FROM WasteTruck wt WHERE wt.availabilityStatus = :status", WasteTruck.class)
                .setParameter("status", WasteTruck.AvailabilityStatus.AVAILABLE)
                .getResultList();
    }

}
