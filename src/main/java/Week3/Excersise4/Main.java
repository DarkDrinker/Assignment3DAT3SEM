package Week3.Excersise4;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        PointDAO pointDAO = new PointDAO();

        System.out.println(pointDAO.NumberOfObjectsInDB());
        System.out.println(pointDAO.AverageX());
        for (Point point :
                pointDAO.ListOfPoints()) {
            System.out.println(point.toString());
        }
    }

}
