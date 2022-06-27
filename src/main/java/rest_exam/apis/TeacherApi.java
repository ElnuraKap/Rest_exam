package rest_exam.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.teacher_dto.TeacherRequest;
import rest_exam.dto.teacher_dto.TeacherResponse;
import rest_exam.dto.teacher_dto.TeacherResponseView;
import rest_exam.services.TeacherService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teachers")
public class TeacherApi {

    private final TeacherService service;

    @PostMapping("/save")
    public TeacherResponse create(@RequestBody TeacherRequest request){
        return service.create(request);
    }

    @PutMapping("/update/by/{teacherId}")
    public TeacherResponse update(@PathVariable Long teacherId ,
                                  @RequestBody TeacherRequest request){
        return service.update(teacherId,request);
    }

    @GetMapping("/find/by/{teacherId}")
    public TeacherResponse findById(@PathVariable Long teacherId){
        return service.findById(teacherId);
    }

    @DeleteMapping("/delete/by/{teacherId}")
    public ResponseDelete delete(@PathVariable Long teacherId){
        return service.deleteById(teacherId);
    }

    @GetMapping
    public List<TeacherResponse> getAllTeachers(){
        return service.getAllTeachers();
    }

    @GetMapping("/pagination")
    public TeacherResponseView getAllTeachers(@RequestParam(name = "text",required = false)String text,
                                              @RequestParam int page,
                                              @RequestParam int size){
        return service.getAllTeachersPagination(text, page, size);
    }
}
