package Week4.GLSPart2;

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

    public void createPackage(Week4.GLSPart2.Package pkg) {
        entityManager.getTransaction().begin();
        entityManager.persist(pkg);
        entityManager.getTransaction().commit();
    }

    public Week4.GLSPart2.Package readPackage(Long id) {
        return entityManager.find(Week4.GLSPart2.Package.class, id);
    }
    public Week4.GLSPart2.Package findPackageByTrackingNumber(String trackingNumber) {
        TypedQuery<Week4.GLSPart2.Package> query = entityManager.createQuery("SELECT p FROM Package p WHERE p.trackingNumber = :trackingNumber", Week4.GLSPart2.Package.class);
        query.setParameter("trackingNumber", trackingNumber);
        return query.getSingleResult();
    }

    public void updatePackage(Week4.GLSPart2.Package pkg) {
        entityManager.getTransaction().begin();
        entityManager.merge(pkg);
        entityManager.getTransaction().commit();
    }

    public void updatePackageDeliveryStatus(String trackingNumber, Week4.GLSPart2.Package.DeliveryStatus deliveryStatus) {
        Week4.GLSPart2.Package pkg = findPackageByTrackingNumber(trackingNumber);
        if (pkg != null) {
            entityManager.getTransaction().begin();
            pkg.setDeliveryStatus(deliveryStatus);
            entityManager.getTransaction().commit();
        }
    }

    public void removePackage(Long id) {
        Week4.GLSPart2.Package pkg = entityManager.find(Week4.GLSPart2.Package.class, id);
        if (pkg != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(pkg);
            entityManager.getTransaction().commit();
        }
    }

    public void deletePackage(Long id) {
        Week4.GLSPart2.Package pkg = entityManager.find(Week4.GLSPart2.Package.class, id);
        if (pkg != null) {
            entityManager.getTransaction().begin();
            entityManager.remove(pkg);
            entityManager.getTransaction().commit();
        }
    }

    public List<Week4.GLSPart2.Package> readAllPackages() {
        TypedQuery<Week4.GLSPart2.Package> query = entityManager.createQuery("SELECT p FROM Package p", Week4.GLSPart2.Package.class);
        //something is wrong
        List<Package> packages = query.getResultList();
        return packages;
    }

}
