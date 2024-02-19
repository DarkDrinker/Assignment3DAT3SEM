package Week4.ExcersiseInClass;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "course_name")
    private String name;

    @OneToOne
    private Teacher teacher;

    public Course (String name){
        this.name = name;
    }

    public void setTeacher(Teacher teacher){
        if (teacher != null){
            this.teacher = teacher;
        }
    }
}
