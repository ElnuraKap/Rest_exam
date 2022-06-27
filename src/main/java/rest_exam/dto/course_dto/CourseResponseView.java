package rest_exam.dto.course_dto;

import lombok.Getter;
import lombok.Setter;
import rest_exam.models.Course;

import java.util.List;

@Getter
@Setter
public class CourseResponseView {

    List<CourseResponse> responses;
}
