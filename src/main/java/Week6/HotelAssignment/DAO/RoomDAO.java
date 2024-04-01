package Week6.HotelAssignment.DAO;

import HotelAssignment.model.Room;
import jakarta.persistence.EntityManagerFactory;

public class RoomDAO extends DAO<Room, Integer> {
    public RoomDAO(EntityManagerFactory emf) {
        super(emf);
    }
}
