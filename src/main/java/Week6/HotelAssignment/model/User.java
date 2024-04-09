package Week6.HotelAssignment.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;

    @ManyToMany
    private Set<Role> roles = new HashSet<>();


    public User(String username, String password){
        this.username = username;
        this.password = password;
    }


    @JsonIgnore
    public Set<String> getRolesAsString(){
        if(roles.isEmpty()){
            return null;
        }
        Set<String> rolesAsString = new HashSet<>();
        roles.forEach((role) -> {
            rolesAsString.add(role.getName());
        });
        return rolesAsString;
    }
    public boolean verifyPassword(String password){
        return BCrypt.checkpw(password, this.password);
    }

    public void addRole(Role role){
        roles.add(role);
        role.getUsers().add(this);
    }
    private void removeRole(Role role){
        roles.remove(role);
        role.getUsers().remove(this);
    }
    public boolean verifyUser(String password){
        return BCrypt.checkpw(password,this.password);
    }
    @PrePersist
    public void prePersist(){
        String salt = BCrypt.gensalt();
        this.password = BCrypt.hashpw(password,salt);
    }
}
