package Excersise1And2;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NonNull;

@Getter
@Entity
public class Unicorn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unicorn_id", nullable = false)
    private int id;

    @NonNull
    @Setter
    @Column(name = "name", length = 100)
    private String name;

    @Setter
    @Column(name = "age")
    private int age;

    @Setter
    @Column(name = "power_strength")
    private int powerStrength;

    public Unicorn()
    {
    }
}