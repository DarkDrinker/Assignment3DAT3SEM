package Week6.HotelAssignment.DAO;

import Week6.HotelAssignment.model.Room;
import jakarta.persistence.EntityManagerFactory;

public class RoomDAO extends DAO<Room, Integer> {
    public RoomDAO(EntityManagerFactory emf) {
        super(emf);
    }
}
