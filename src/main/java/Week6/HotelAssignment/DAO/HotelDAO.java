package Week6.HotelAssignment.DAO;

import Week6.HotelAssignment.model.Hotel;

public class HotelDAO extends DAO<Hotel, Integer>{
    private static HotelDAO instance;
    public HotelDAO(Boolean isTesting) {
        super(Hotel.class, isTesting);
    }
    public static HotelDAO getInstance(boolean isTesting){
        if(instance == null){
            instance = new HotelDAO(isTesting);
        }
        return instance;
    }
}
