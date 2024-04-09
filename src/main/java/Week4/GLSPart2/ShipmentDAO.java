package Week4.GLSPART2;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ShipmentDAO {
    private EntityManager em;

    public ShipmentDAO() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gls-persistence-unit");
        em = emf.createEntityManager();
    }

    public void save(Shipment shipment) {
        em.getTransaction().begin();
        em.persist(shipment);
        em.getTransaction().commit();
    }

    public Shipment findById(int id) {
        Shipment foundShipment = em.find(Shipment.class, id);
        em.close();
        return foundShipment;
    }

    public void update(Shipment shipment) {
        em.getTransaction().begin();
        em.merge(shipment);
        em.getTransaction().commit();
    }

    public void delete(int id) {
        em.getTransaction().begin();
        Shipment shipment = findById(id);
        if (shipment != null) {
            em.remove(shipment);
        }
        em.getTransaction().commit();
    }
}