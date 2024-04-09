package Week6.HotelAssignment.daos;

import Week6.HotelAssignment.DAO.UserDAO;
import Week6.HotelAssignment.config.ApplicationConfig;
import Week6.HotelAssignment.config.HibernateConfig;
import Week6.HotelAssignment.config.Routes;
import Week6.HotelAssignment.model.*;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserDAOTest {

    private static EntityManagerFactory emf;
    private static User userManager, userAdmin;
    private static Role manager, admin;
    private static UserDAO userDAO;

    @BeforeAll
    static void setUpAll(){
        userDAO = UserDAO.getInstance(true);
        emf = HibernateConfig.getEntityManagerFactoryConfig(true);

        RestAssured.baseURI = "http://localhost:7778/api";
        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig.initiateServer()
                .startServer(7778)
                .setExceptionHandling()
                .setRoute(Routes.getRoutes(true))
                .checkSecurityRoles(true);

        manager = new Role("MANAGER");
        admin = new Role("ADMIN");

        userManager = new User("PatrickStudent","test");
        userAdmin = new User("PatrickAdmin","test");
        userAdmin.addRole(admin);
        userManager.addRole(manager);

        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
            em.persist(manager);
            em.persist(admin);
            em.persist(userManager);
            em.persist(userAdmin);
            em.getTransaction().commit();
        }
    }

    @BeforeEach
    void setUp() {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();
        }
    }

    @AfterAll
    static void tearDown(){
        try(EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("delete from Role").executeUpdate();
            em.createQuery("delete from users").executeUpdate();
            em.getTransaction().commit();
        }
    }
    @Test
    void createUser() {
        User user = userDAO.createUser("Hansi","1234");
        assertNotNull(user);
        assertEquals("Hansi",user.getUsername());
    }

    @Test
    void getVerifiedUser() {
        User user = userDAO.getVerifiedUser("PatrickAdmin", "test");
        assertNotNull(user);
    }
}