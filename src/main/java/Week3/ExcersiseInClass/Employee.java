package Week3.ExcersiseInClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Entity
@Table
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Employee_id", nullable = false, unique = true)
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
    @Column(name = "salary")
    private int salary;
    @Setter
    @Column(name = "department")
    private String department;

}
