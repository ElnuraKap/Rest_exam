package rest_exam.mappers.course_mapper;

import org.springframework.stereotype.Component;
import rest_exam.dto.course_dto.CourseResponse;
import rest_exam.models.Course;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseViewMapper {

    public CourseResponse viewCourse(Course course){

        if(course == null){
            return null;
        }
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setCourseName(course.getCourseName());
        response.setDuration(course.getDuration());
        response.setActive(course.isActive());
        response.setCreated(course.getCreated());
        return response;
    }

    public List<CourseResponse> view (List<Course> courses){
        List<CourseResponse> responses = new ArrayList<>();
        for(Course course : courses) {
            responses.add(viewCourse(course));
        }
        return responses;
    }
}
