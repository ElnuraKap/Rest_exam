package rest_exam.dto.teacher_dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
public class TeacherResponse {

    private Long id;

    private String firstName;

    private String email;

    private String lastName;

    @CreatedDate
    private LocalDate created;

    private boolean isActive;

}
