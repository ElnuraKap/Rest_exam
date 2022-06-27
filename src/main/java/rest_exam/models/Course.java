package rest_exam.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Course {

    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "course_sequence")
    private Long id;

    private String courseName;

    private String duration;

    @CreatedDate
    private LocalDate created;

    private boolean isActive;


    @ManyToOne(cascade = {REFRESH,DETACH,MERGE})
    private Company company;

    @ManyToMany
            (mappedBy = "courses",cascade = {MERGE,REMOVE} )

    private List<Group> groups = new ArrayList<>();

    @OneToOne(mappedBy = "course",cascade = REMOVE)
    private Teacher teacher;

    public Course(String courseName, String duration) {
        this.courseName = courseName;
        this.duration = duration;
    }


    public void setGroup(Group group) {
        this.groups.add(group);
    }


}


