package rest_exam.mappers.teacher_mapper;

import org.springframework.stereotype.Component;
import rest_exam.dto.teacher_dto.TeacherRequest;
import rest_exam.models.Teacher;

import java.time.LocalDate;

@Component
public class TeacherEditMapper {

    public Teacher create(TeacherRequest request){

        if(request == null){
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setFirstName(request.getFirstName());
        teacher.setLastName(request.getLastName());
        teacher.setEmail(request.getEmail());
        teacher.setCreated(LocalDate.now());
        teacher.setActive(true);
        return teacher;
    }

    public void update(Teacher teacher,TeacherRequest teacherRequest){
        teacher.setFirstName(teacherRequest.getFirstName());
        teacher.setLastName(teacherRequest.getLastName());
        teacher.setEmail(teacherRequest.getEmail());
    }
}
