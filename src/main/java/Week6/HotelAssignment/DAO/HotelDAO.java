package Week6.HotelAssignment.DAO;

import HotelAssignment.model.Hotel;
import jakarta.persistence.EntityManagerFactory;

public class HotelDAO extends DAO<Hotel, Integer>{
    private static HotelDAO instance;
    public HotelDAO(EntityManagerFactory emf) {
        super(emf);
    }
    public static HotelDAO getInstance(boolean isTest){
        if(instance == null){
            instance = new HotelDAO(emf);
        }
        return instance;
    }
}
