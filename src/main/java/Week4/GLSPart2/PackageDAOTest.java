package Week4.GLSPart2;

import Week3.Excersise7GLS.Package;
import Week3.Excersise7GLS.PackageDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    public class PackageDAOTest {
        private static EntityManager entityManager;
        private static Week3.Excersise7GLS.PackageDAO packageDAO;

        @BeforeAll
        public static void setUp() {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("gls-persistence-unit");
            entityManager = emf.createEntityManager();
            packageDAO = new PackageDAO();
        }

        @AfterAll
        public static void tearDown() {
            entityManager.close();
        }

        @Test
        public void testPersistPackage() {
            Week3.Excersise7GLS.Package pkg = new Week3.Excersise7GLS.Package();
            pkg.setTrackingNumber("ABC123");
            pkg.setSenderName("Sender");
            pkg.setReceiverName("Receiver");
            pkg.setDeliveryStatus(Week3.Excersise7GLS.Package.DeliveryStatus.PENDING);

            packageDAO.createPackage(pkg);

            Week3.Excersise7GLS.Package retrievedPackage = entityManager.find(Package.class, pkg.getId());
            Assertions.assertNotNull(retrievedPackage);
            Assertions.assertEquals("ABC123", retrievedPackage.getTrackingNumber());
        }

    }