package Week3.Excersise7GLS;

import Week3.ExcersiseInClass.HibernateConfig;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class PackageDAO {
    private EntityManager entityManager;

    public PackageDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gls-persistence-unit");
        entityManager = emf.createEntityManager();
    }

    public void createPackage(Package pkg) {
        entityManager.getTransaction().begin();
        entityManager.persist(pkg);
        entityManager.getTransaction().commit();
    }

    public Package readPackage(Long id) {
        return entityManager.find(Package.class, id);
    }
    public Package findPackageByTrackingNumber(String trackingNumber) {
        TypedQuery<Package> query = entityManager.createQuery("SELECT p FROM Package p WHERE p.trackingNumber = :trackingNumber", Package.class);
        query.setParameter("trackingNumber", trackingNumber);
        return query.getSingleResult();
    }

    public void updatePackage(Package pkg) {
        entityManager.getTransaction().begin();
        entityManager.merge(pkg);
        entityManager.getTransaction().commit();
    }

    public void updatePackageDeliveryStatus(String trackingNumber, Package.DeliveryStatus deliveryStatus) {
        Package pkg = findPackageByTrackingNumber(trackingNumber);
        if (pkg != null) {
            entityManager.getTransaction().begin();
            pkg.setDeliveryStatus(deliveryStatus);
            entityManager.getTransaction().commit();
        }
    }

    public void removePackage(Long id) {
        Package pkg = entityManager.find(Package.class, id);
        if (pkg != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(pkg);
            entityManager.getTransaction().commit();
        }
    }

    public void deletePackage(Long id) {
        Package pkg = entityManager.find(Package.class, id);
        if (pkg != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(pkg);
            entityManager.getTransaction().commit();
        }
    }

    public List<Package> readAllPackages() {
        TypedQuery<Package> query = entityManager.createQuery("SELECT p FROM Package p", Package.class);
        return query.getResultList();
    }

}
