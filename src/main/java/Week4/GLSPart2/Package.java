package Week4.GLSPART2;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String trackingNumber;
    private String senderName;
    private String receiverName;
    @Enumerated(EnumType.STRING)
    private DeliveryStatus deliveryStatus;

    @OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL)
    private Set<Shipment> shipment = new HashSet<>();

    private LocalDateTime updatedDate;
    private LocalDateTime createdDate;


    public enum DeliveryStatus{
        PENDING,
        IN_TRANSIT,
        DELIVERED
    }

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

