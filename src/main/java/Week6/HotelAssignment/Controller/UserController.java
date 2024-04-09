package Week6.HotelAssignment.Controller;

import Week6.HotelAssignment.DAO.UserDAO;

public class UserController {

    private static UserDAO userDAO;
    public UserController(boolean isTest){
        userDAO = UserDAO.getInstance(isTest);
    }



}