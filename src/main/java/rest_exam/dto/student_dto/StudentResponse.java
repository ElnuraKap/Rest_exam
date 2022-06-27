package rest_exam.dto.student_dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import rest_exam.enam.StudyFormat;

import java.time.LocalDate;

@Getter
@Setter
public class StudentResponse {

    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    private StudyFormat studyFormat;

    @CreatedDate
    private LocalDate created;

    private boolean idActive;
}
