package Week4.Recycling;

import Week4.Recycling.model.*;
import Week4.Recycling.config.*;
import Week4.Recycling.dao.*;

import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = HibernateConfig.getEntityManagerFactoryConfig();

        DriverDAOImpl driverDAO = new DriverDAOImpl(emf);
        driverDAO.saveDriver("John", "Doe", new BigDecimal("2500"));

        Driver driver = driverDAO.getDriverById("1");
        System.out.println("Retrieved Driver: " + driver);

        driver.setSalary(new BigDecimal("3000"));
        driverDAO.updateDriver(driver);

        driverDAO.deleteDriver("1");

        List<Driver> driversSameYear = driverDAO.findAllDriversEmployedAtTheSameYear("2023");
        System.out.println("Drivers employed at the same year: " + driversSameYear);

        List<Driver> highSalaryDrivers = driverDAO.fetchAllDriversWithSalaryGreaterThan10000();
        System.out.println("Drivers with salary greater than 10000: " + highSalaryDrivers);

        double highestSalary = driverDAO.fetchHighestSalary();
        System.out.println("Highest salary: " + highestSalary);

        List<String> firstNames = driverDAO.fetchFirstNameOfAllDrivers();
        System.out.println("First names of all drivers: " + firstNames);

        long numberOfDrivers = driverDAO.calculateNumberOfDrivers();
        System.out.println("Number of drivers: " + numberOfDrivers);

        Driver highestSalaryDriver = driverDAO.fetchDriverWithHighestSalary();
        System.out.println("Driver with the highest salary: " + highestSalaryDriver);

        WasteTruckDAOImpl wasteTruckDAO = new WasteTruckDAOImpl(emf);

        wasteTruckDAO.saveWasteTruck("Volvo", "A123-45-B6", 8000);

        WasteTruck wasteTruck = wasteTruckDAO.getWasteTruckById(1);
        System.out.println("Retrieved WasteTruck: " + wasteTruck);

        wasteTruckDAO.setWasteTruckAvailable(wasteTruck, WasteTruck.AvailabilityStatus.AVAILABLE);

        wasteTruckDAO.deleteWasteTruck(1);

        List<WasteTruck> availableTrucks = wasteTruckDAO.getAllAvailableTrucks();
        System.out.println("Available trucks: " + availableTrucks);

        emf.close();
    }
}

