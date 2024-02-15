package Week3.Excersise7GLS;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

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

