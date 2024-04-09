package Week6.HotelAssignment;

import Week6.HotelAssignment.config.ApplicationConfig;
import Week6.HotelAssignment.config.Routes;


public class Main {

    public static void main(String[] args) {
        boolean isTesting = false;

        ApplicationConfig app = ApplicationConfig.getInstance()
                .initiateServer()
                .startServer(7008)
                .setExceptionHandling()
                .setRoute(Routes.getRoutes(isTesting))
                .checkSecurityRoles(isTesting);
    }
}