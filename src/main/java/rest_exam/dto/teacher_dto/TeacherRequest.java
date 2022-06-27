package rest_exam.dto.teacher_dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TeacherRequest {

    private String firstName;

    private String email;

    private String lastName;

    private Long courseId;
}
