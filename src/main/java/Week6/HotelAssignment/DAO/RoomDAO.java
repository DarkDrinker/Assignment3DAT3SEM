package Week6.HotelAssignment.DAO;

import Week6.HotelAssignment.model.Room;

public class RoomDAO extends DAO<Room, Integer> {

    private static RoomDAO instance;
    public RoomDAO(Boolean isTesting) {
        super(Room.class, isTesting);
    }

    public static RoomDAO getInstance(boolean isTesting){
        if(instance == null){
            instance = new RoomDAO(isTesting);
        }
        return instance;
    }
}
