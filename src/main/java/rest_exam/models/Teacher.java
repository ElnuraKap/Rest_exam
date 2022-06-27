package rest_exam.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name ="teachers")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Teacher {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,
                    generator = "teacher_id_generator"
            )
    @SequenceGenerator(
            name = "teacher_id_generator",
            sequenceName = "teacher_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    @CreatedDate
    private LocalDate created;

    private boolean isActive;


    @OneToOne(cascade = {MERGE,PERSIST,DETACH})
    @JoinColumn(name = "course_id")
    private Course course;

    public Teacher(String firstName, String email, String lastName) {
        this.firstName = firstName;
        this.email = email;
        this.lastName = lastName;
    }
}
