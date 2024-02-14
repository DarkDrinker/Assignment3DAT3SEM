package Week3.Excersise3;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table

public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id", nullable = false, unique = true)
    private Long id;
    @Setter
    @Column(name = "firstName", length = 100)
    private String firstName;
    @Setter
    @Column(name = "LastName", length = 100)
    private String lastName;
    @Setter
    @Column(name = "email", length = 100, unique = true)
    private String email;
    @Setter
    @Column(name = "age", length = 100)
    private int age;

    @PrePersist
    //@PreUpdate
    public void validateEmail() {
        if (email != null && !email.matches("[a-zA-Z0-9]+@[a-zA-Z0-9]")) {
            throw new IllegalArgumentException("Invalid email address");
        }
    }
}
