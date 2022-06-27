package rest_exam.dto.student_dto;

import lombok.Getter;
import lombok.Setter;
import rest_exam.enam.StudyFormat;

@Getter
@Setter
public class StudentRequest {

    private String firstName;

    private String email;

    private String lastName;

    private StudyFormat studyFormat;

    private Long groupId;
}
