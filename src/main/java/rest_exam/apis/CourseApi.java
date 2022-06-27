package rest_exam.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.course_dto.CourseRequest;
import rest_exam.dto.course_dto.CourseResponse;
import rest_exam.dto.course_dto.CourseResponseView;
import rest_exam.services.CourseService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/courses")
@PreAuthorize("hasAnyAuthority('BOSS')")
public class CourseApi {

    private final CourseService service;

    @PostMapping("/save")
    public CourseResponse create(@RequestBody CourseRequest request){
        return service.create(request.getCompanyId(),request);
    }

    @PutMapping("/update/by/{courseId}")
    public CourseResponse update(@PathVariable Long courseId,
                                 @RequestBody CourseRequest courseRequest){
        return service.update(courseId,courseRequest);
    }

    @GetMapping("/find/by/{courseId}")
    public CourseResponse findById(@PathVariable Long courseId){
        return service.findById(courseId);
    }

    @DeleteMapping("/delete/by/{courseId}")
    public ResponseDelete delete(@PathVariable Long courseId){
        return service.deleteById(courseId);
    }

    @GetMapping
    public List<CourseResponse> getAllCourses(){
        return service.getAllCourse();
    }

    @GetMapping("/pagination")
    public CourseResponseView getAllCourses(@RequestParam(name = "text",required = false)String text,
                                            @RequestParam int page,
                                            @RequestParam int size){
        return service.getAllCoursesPagination(text, page, size);

    }
}
