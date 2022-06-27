package rest_exam.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import rest_exam.enam.StudyFormat;

import javax.persistence.*;

import java.time.LocalDate;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Student {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,
                    generator = "student_id_generator"
            )
    @SequenceGenerator(
            name = "student_id_generator",
            sequenceName = "student_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    private StudyFormat studyFormat;

    @CreatedDate
    private LocalDate created;

    private boolean idActive;


    @ManyToOne(cascade = {MERGE,PERSIST,DETACH})
    @JoinColumn(name = "group_id")
    private Group group;

    public void setGroup(Group group) {
        this.group = group;
    }

}
