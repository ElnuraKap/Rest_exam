package rest_exam.mappers.teacher_mapper;

import org.springframework.stereotype.Component;
import rest_exam.dto.teacher_dto.TeacherResponse;
import rest_exam.models.Teacher;

import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherViewMapper {

    public TeacherResponse viewTeacher(Teacher teacher){

        if(teacher == null){
            return null;
        }
        TeacherResponse response = new TeacherResponse();
        response.setId(teacher.getId());
        response.setFirstName(teacher.getFirstName());
        response.setLastName(teacher.getLastName());
        response.setEmail(teacher.getEmail());
        response.setActive(teacher.isActive());
        response.setCreated(teacher.getCreated());
        return response;
    }

    public List<TeacherResponse> view (List<Teacher> teachers){
        List<TeacherResponse> responses = new ArrayList<>();
        for(Teacher teacher : teachers){
            responses.add(viewTeacher(teacher));
        }
        return responses;
    }
}
