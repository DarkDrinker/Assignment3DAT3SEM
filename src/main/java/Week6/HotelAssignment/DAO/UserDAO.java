package Week6.HotelAssignment.DAO;

import Week6.HotelAssignment.model.Role;
import Week6.HotelAssignment.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;

public class UserDAO extends DAO<User, String> implements ISecurityDAO<User, Role>{
    private static UserDAO instance;

    private UserDAO(boolean isTesting) {
        super(User.class, isTesting);
    }

    public static UserDAO getInstance(boolean isTesting){
        if(instance == null){
            instance = new UserDAO(isTesting);
        }
        return instance;
    }




    public User getVerifiedUser(String username,String password){
        EntityManager em = emf.createEntityManager();
        //User userToFind = em.find(User.class,username);
        User userToFind = em.createQuery("SELECT u FROM users u WHERE u.username=:username", User.class)
                .setParameter("username", username)
                .getSingleResult();
        if(userToFind == null) throw new EntityNotFoundException("No user with username: " + username +" was found");
        if(!userToFind.verifyUser(password)){
            throw new EntityNotFoundException("Wrong password");
        }
        return userToFind;
    }


    public User createUser(String username, String password) {
    User user = new User();
    user.setUsername(username);
    user.setPassword(password);
    try(var em = emf.createEntityManager()){
        Role role = em.find(Role.class, "MANAGER");
        user.addRole(role);
        em.getTransaction().begin();
        em.persist(user);
        em.getTransaction().commit();
    }
    return user;
    }

    @Override
    public Role createRole(String role) {
        return null;
    }

    @Override
    public Void AddRoleToUser(Role role) {return null;}
}
