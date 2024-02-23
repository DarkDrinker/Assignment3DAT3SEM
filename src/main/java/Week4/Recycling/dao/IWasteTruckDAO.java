package Week4.Recycling.dao;

import Week4.Recycling.model.Driver;
import Week4.Recycling.model.WasteTruck;

import java.math.BigDecimal;
import java.util.List;

public interface IWasteTruckDAO {
    // WasteTruck
    void saveWasteTruck(String brand, String registrationNumber, int capacity);

    WasteTruck getWasteTruckById(int id);
    void setWasteTruckAvailable(WasteTruck wasteTruck, WasteTruck.AvailabilityStatus availabilityStatus);
    void deleteWasteTruck(int id);
    void addDriverToWasteTruck(WasteTruck wasteTruck, Driver driver);
    void removeDriverFromWasteTruck(WasteTruck wasteTruck, String id);
    List<WasteTruck> getAllAvailableTrucks();
}
