package Week4.Recycling.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "wastetruck")
public class WasteTruck {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private int id;

        private String brand;
        private int capacity;

        @Enumerated(EnumType.STRING)
        private AvailabilityStatus availabilityStatus;

        private String registrationNumber;

        // Relationer 1:m
        @OneToMany(mappedBy = "WasteTruck", cascade = CascadeType.ALL)
        private Set<Driver> driver = new HashSet<>();

    public WasteTruck(String brand, int capacity, String registration_number) {
        this.brand = brand;
        this.capacity = capacity;
        this.registrationNumber = registration_number;
    }

    public enum AvailabilityStatus {
        AVAILABLE,
        NOT_AVAILABLE
    }
}
