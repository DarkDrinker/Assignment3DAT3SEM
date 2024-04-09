package Week6.HotelAssignment.Controller;

import Week6.HotelAssignment.config.ApplicationConfig;
import Week6.HotelAssignment.config.HibernateConfig;
import Week6.HotelAssignment.config.Routes;
import Week6.HotelAssignment.model.Hotel;
import Week6.HotelAssignment.model.Role;
import Week6.HotelAssignment.model.Room;
import Week6.HotelAssignment.model.User;
import io.restassured.RestAssured;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.eclipse.jetty.http.HttpStatus;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

class HotelControllerTest {

    private static EntityManagerFactory emf;
    private static User userManager, userAdmin;
    private static Role manager, admin;
    private static Object adminToken;
    private static Object managerToken;

    @BeforeAll
    static void setUpAll(){
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
        adminToken = getToken(userAdmin.getUsername(), "test");
        managerToken = getToken(userManager.getUsername(), "test");
    }

    @BeforeEach
    void setUp() {
        try(EntityManager em = emf.createEntityManager()){
            em.getTransaction().begin();

            //em.createQuery("delete from users").executeUpdate();
            em.createQuery("delete from Room").executeUpdate();
            em.createQuery("delete from Hotel").executeUpdate();


            em.createNativeQuery("ALTER SEQUENCE hotel_id_seq RESTART WITH 1").executeUpdate();
            em.createNativeQuery("ALTER SEQUENCE room_id_seq RESTART WITH 1").executeUpdate();


            Hotel h1 = new Hotel("Hilton", "Fiskegade 5");
            Hotel h2 = new Hotel("Fly Savings", "Lufthavnsgade 1");
            Hotel h3 = new Hotel("Airport Hotel", "Lufthavnsvej 2");
            Room r1_1 = new Room(1, 1, 1000);
            Room r1_2 = new Room(1, 2, 100);
            Room r2_1 = new Room(2, 1, 10);
            Room r2_2 = new Room(2, 2, 100);
            Room r3_1 = new Room(3, 1, 1000);
            Room r3_2 = new Room(3, 2, 100);

            h1.addRoom(r1_1);
            h1.addRoom(r1_2);
            h2.addRoom(r2_1);
            h2.addRoom(r2_2);
            h3.addRoom(r3_1);
            h3.addRoom(r3_2);

            em.persist(h1);
            em.persist(h2);
            em.persist(h3);
            em.getTransaction().commit();
            em.clear();
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

    public static Object getToken(String username, String password)
    {
        return login(username, password);
    }

    private static Object login(String username, String password)
    {
        String json = String.format("{\"username\": \"%s\", \"password\": \"%s\"}", username, password);

        var token = given()
                .contentType("application/json")
                .body(json)
                .when()
                .post("http://localhost:7778/api/auth/login")
                .then()
                .extract()
                .response()
                .body()
                .path("token");
        return "Bearer " + token;
    }

    @Test
    void createHotel() {
        String json = "{\"name\":\"Star Hotel\",\"address\":\"Starvej 7\",\"rooms\":[{\"number\":1,\"price\":5000},{\"number\":2,\"price\":3000}]}";

        RestAssured.given()
                .header("Authorization", adminToken)
                .contentType("application/json")
                .body(json)
                .when()
                .post("http://localhost:7778/api/hotels")
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.OK_200)
                .body("name", equalTo("Star Hotel"))
                .body("rooms.size()",equalTo(2));
    }

    @Test
    void getHotels() {
        RestAssured.given()
                .header("Authorization", managerToken)
                .contentType("application/json")
                .when()
                .get("http://localhost:7778/api/hotels")
                .then().log().all()
                .assertThat()
                .statusCode(HttpStatus.OK_200)
                .body("hotel.size()",equalTo(3));
    }

}