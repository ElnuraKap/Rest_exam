package rest_exam.dto.course_dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
public class CourseResponse {

    private Long id;

    private String courseName;

    private String duration;

    @CreatedDate
    private LocalDate created;

    private boolean isActive;

}
