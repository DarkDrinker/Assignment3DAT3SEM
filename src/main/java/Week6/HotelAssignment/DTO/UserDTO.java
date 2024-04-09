package Week6.HotelAssignment.DTO;

import Week6.HotelAssignment.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class UserDTO {

    private String username;
    private String password;
    private Set<String> roles;


    public UserDTO(String username, Set<String> rolesSet) {
        this.username = username;
        this.roles = rolesSet;
    }

    public UserDTO(User user){
        this.username = user.getUsername();
        this.password = user.getPassword();
        this.roles = user.getRolesAsString();
    }
}