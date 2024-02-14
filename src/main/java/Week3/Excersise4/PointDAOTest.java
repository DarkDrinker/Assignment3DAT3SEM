package Week3.Excersise4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PointDAOTest {
    private static EntityManagerFactory emf;
    private static EntityManager em;
    private static PointDAO pointDAO;

    @BeforeAll
    public static void setUp() {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();
        em = emf.createEntityManager();
        PointDAO pointDAO = new PointDAO();
    }

    @AfterAll
    public static void tearDown() {
        em.close();
        emf.close();
    }

    @Test
    void fillDB() {
        pointDAO.fillDB();
        long totalPoints = pointDAO.NumberOfObjectsInDB();
        assertEquals(1000, totalPoints);
    }

    @Test
    void numberOfObjectsInDB() {
        long totalPoints = pointDAO.NumberOfObjectsInDB().longValue();
        assertEquals(1000, totalPoints);
    }

    @Test
    void averageX() {
        double averageX = (double) pointDAO.AverageX();
        assertEquals(499.5, averageX);
    }

    @Test
    void listOfPoints() {
        List<Point> points = pointDAO.ListOfPoints();
        assertEquals(1000, points.size());
    }
}