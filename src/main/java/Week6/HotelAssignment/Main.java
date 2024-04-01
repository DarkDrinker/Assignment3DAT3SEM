package Week6.HotelAssignment;


import HotelAssignment.DAO.HotelDAO;
import HotelAssignment.DAO.RoomDAO;
import HotelAssignment.config.ApplicationConfig;
import HotelAssignment.config.HibernateConfig;
import HotelAssignment.config.Routes;
import HotelAssignment.model.*;
import jakarta.persistence.EntityManagerFactory;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        ApplicationConfig applicationConfig = ApplicationConfig.getInstance();
        applicationConfig.initiateServer()
                .startServer(7007)
                .setExceptionHandling()
                .setRoute(Routes.setRoutes());
        setup(emf);
    }

    private static void setup(EntityManagerFactory emf) {
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
        HotelDAO hotelDAO = new HotelDAO(emf);
        RoomDAO roomDAO = new RoomDAO(emf);

        hotelDAO.create(h1);
        hotelDAO.create(h2);
        hotelDAO.create(h3);

        roomDAO.create(r1_1);
        roomDAO.create(r1_2);
        roomDAO.create(r2_1);
        roomDAO.create(r2_2);
        roomDAO.create(r3_1);
        roomDAO.create(r3_2);
    }
}