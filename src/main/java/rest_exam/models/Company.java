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

import static javax.persistence.CascadeType.ALL;

@Entity
@Table(name = "companies")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Company {

    @Id
    @GeneratedValue
            (strategy = GenerationType.SEQUENCE,
                    generator = "company_id_generator"
            )
    @SequenceGenerator(
            name = "company_id_generator",
            sequenceName = "company_id_seq",
            allocationSize = 1
    )

    private Long id;

    private String companyName;

    private String locatedCountry;

    @CreatedDate
    private LocalDate created;

    private boolean isActive;

    @OneToMany(cascade = ALL, orphanRemoval = true,mappedBy = "company")
    private List<Course> course = new ArrayList<>();

    public Company(String companyName, String locatedCountry) {
        this.companyName = companyName;
        this.locatedCountry = locatedCountry;
    }
    public void setCourse(Course course) {
        this.course.add(course);
    }
}
