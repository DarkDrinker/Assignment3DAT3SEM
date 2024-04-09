package Week6.HotelAssignment.DAO;


import Week6.HotelAssignment.Exceptions.ValidationException;

public interface ISecurityDAO<U,R> {
    U getVerifiedUser(String username, String password) throws ValidationException;
    U createUser(String username, String password);
    R createRole(String role);
    Void AddRoleToUser(R r);
}
