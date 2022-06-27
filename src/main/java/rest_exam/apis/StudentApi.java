package rest_exam.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.student_dto.StudentRequest;
import rest_exam.dto.student_dto.StudentResponse;
import rest_exam.dto.student_dto.StudentResponseView;
import rest_exam.services.StudentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentApi {

    private final StudentService service;

    @PostMapping("/save")
    public StudentResponse create(@RequestBody StudentRequest request){
        return service.create(request);
    }

    @PutMapping("/update/by/{studentId}")
    public StudentResponse update(@PathVariable Long studentId,
                                  @RequestBody StudentRequest studentRequest){
        return service.update(studentId,studentRequest);
    }

    @GetMapping("/find/by/{studentId}")
    public StudentResponse findById(@PathVariable Long studentId){
        return service.findById(studentId);
    }

    @DeleteMapping("/delete/by/{studentId}")
    public ResponseDelete delete(@PathVariable Long studentId){
        return service.deleteById(studentId);
    }

    @GetMapping
    public List<StudentResponse> getAllStudents(){
        return service.getAllStudents();
    }

    @GetMapping("/pagination")
    public StudentResponseView getAllStudents(@RequestParam(name = "text",required = false)String text,
                                              @RequestParam int page,
                                              @RequestParam int size){
        return service.getAllStudentsPagination(text, page, size);
    }

}
