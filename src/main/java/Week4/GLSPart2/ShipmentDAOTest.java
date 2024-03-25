package Week4.GLSPart2;

import Week3.Excersise7GLS.PackageDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipmentDAOTest {
    private static EntityManager entityManager;
    private static Week4.GLSPart2.ShipmentDAO shipmentDAO;

    @BeforeAll
    public static void setUp() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gls-persistence-unit");
        entityManager = emf.createEntityManager();
        shipmentDAO = new ShipmentDAO();
    }

    @AfterAll
    public static void tearDown() {
        entityManager.close();
    }

    @Test
    void save() {

    }

    @Test
    void findById() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }
}