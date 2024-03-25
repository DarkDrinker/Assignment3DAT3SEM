package Week4.GLSPart2;

import jakarta.persistence.*;
import lombok.*;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Shipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int Id;
    @ManyToOne
    private Package aPackage;
    @ManyToOne
    private Location sourcelocation;
    @ManyToOne
    private Location destinationlocation;

    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
        updatedDate = createdDate;
    }

    @PreUpdate
    public void preUpdate() {
        updatedDate = LocalDateTime.now();
    }

}
