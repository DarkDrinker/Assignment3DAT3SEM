package Week4.Recycling.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "driver")
public class Driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;
    private String surname;
    private BigDecimal salary;

    @Temporal(TemporalType.DATE)
    private Date employment_date;

    @ManyToOne
    private WasteTruck wasteTruck;



    public Driver(String name, String surname, BigDecimal salary) {
        this.name = name;
        this.surname = surname;
        this.salary = salary;
    }

    @PrePersist
    protected void onCreate() {
        this.id = generateId();
        this.employment_date = new java.util.Date();
    }

    public String generateId() {
        //ddMMyy-XX-XXXL
        String datePart = String.format("%ty%tm%td", employment_date, employment_date, employment_date);
        String initials = String.valueOf(name.charAt(0)) + String.valueOf(surname.charAt(0));
        int random = (int) (Math.random() * (999 - 100 + 1) + 100);
        char lastLetter = surname.charAt(surname.length() - 1);
        return String.format("%s-%s-%d%s", datePart, initials.toUpperCase(), random, lastLetter);
    }

    public Boolean validateDriverId(String driverId) {
        return driverId.matches("[0-9][0-9][0-9][0-9][0-9][0-9]-[A-Z][A-Z]-[0-9][0-9][0-9][A-Z]");
    }

}
