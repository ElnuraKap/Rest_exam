package rest_exam.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.REMOVE;

@Entity
@Table(name = "groups")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Group {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,
                    generator = "group_id_generator"
            )
    @SequenceGenerator(
            name = "group_id_generator",
            sequenceName = "group_id_seq",
            allocationSize = 1
    )
    private Long id;

    private String groupName;

    private String dateOfStart;

    private String dateOfFinish;

    @CreatedDate
    private LocalDate created;

    private boolean isActive;


    @ManyToMany
//            (cascade = MERGE, fetch = FetchType.EAGER)
    private List<Course> courses = new ArrayList<>();

    @OneToMany(mappedBy = "group",cascade = REMOVE)
//    @Fetch(value = FetchMode.SUBSELECT)
    private List <Student> students = new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private Company company;


    public void setCourse(Course course) {
        this.courses.add(course);
    }

    public void setStudent(Student student) {
        this.students.add(student);
    }
}
