package rest_exam.dto.course_dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CourseRequest {

    private String courseName;

    private String duration;

    public Long companyId;
}
