package rest_exam.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.student_dto.StudentRequest;
import rest_exam.dto.student_dto.StudentResponse;
import rest_exam.dto.student_dto.StudentResponseView;
import rest_exam.exeptions.not_found.CompanyNotFoundException;
import rest_exam.exeptions.not_found.GroupNotFoundException;
import rest_exam.exeptions.not_found.StudentNotFoundException;
import rest_exam.mappers.student_mapper.StudentEditMapper;
import rest_exam.mappers.student_mapper.StudentViewMapper;
import rest_exam.models.Group;
import rest_exam.models.Student;
import rest_exam.repositories.GroupRepository;
import rest_exam.repositories.StudentRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repository;
    private final StudentEditMapper editMapper;
    private final StudentViewMapper viewMapper;
    private final GroupRepository groupRepository;

    public StudentResponse create(StudentRequest studentRequest){
        Student student = editMapper.create(studentRequest);
        student.setGroup(setStudentForGroup(studentRequest.getGroupId()));
        repository.save(student);
        return viewMapper.viewStudent(student);
    }

    private Group setStudentForGroup(Long groupId){
        return groupRepository.findById(groupId)
                .orElseThrow(()-> new StudentNotFoundException(groupId));
    }

    public StudentResponse update(Long id,StudentRequest studentRequest){
        Student student = getStudentById(id);
        editMapper.update(student,studentRequest);
        return viewMapper.viewStudent(repository.save(student));
    }

    private Student getStudentById(Long id) {
        return repository.findById(id)
                .orElseThrow(()-> new StudentNotFoundException(id));
    }

    public StudentResponse findById(Long id){
        Student student = getStudentById(id);
        return viewMapper.viewStudent(student);
    }

    public ResponseDelete deleteById (Long id ){
        boolean exist = repository.existsById(id);
        if(!exist){
            throw new CompanyNotFoundException(id);
        }
        repository.deleteById(id);
        return new ResponseDelete(
                "DELETED",
                "Successfully deleted student");
    }

    public List<StudentResponse> getAllStudents(){
        return viewMapper.view(repository.findAll());
    }

    public StudentResponseView getAllStudentsPagination(String text, int page, int size){
        StudentResponseView responseView = new StudentResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text,pageable)));
        return responseView;
    }

    public List<StudentResponse> view(List<Student> students){
        List<StudentResponse> responses = new ArrayList<>();
        for(Student student : students){
            responses.add(viewMapper.viewStudent(student));
        }
        return responses ;
    }

    public List<Student> search(String name, Pageable pageable){
        String text = name == null ?"":name;
        return repository.searchAndPagination(text.toUpperCase(),pageable);
    }
}
