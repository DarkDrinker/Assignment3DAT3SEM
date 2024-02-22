package Week4.Dolphin;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class Note
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    private String note;
    private LocalDateTime createdDate;

    @ManyToOne
    private Person person;

    public Note(String note)
    {
        this.note = note;
    }

    @PrePersist
    public void prePersist() {
        createdDate = LocalDateTime.now();
    }


}