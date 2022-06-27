package rest_exam.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.course_dto.CourseRequest;
import rest_exam.dto.course_dto.CourseResponse;
import rest_exam.dto.course_dto.CourseResponseView;
import rest_exam.exeptions.not_found.CompanyNotFoundException;
import rest_exam.exeptions.not_found.CourseNorFoundException;
import rest_exam.mappers.course_mapper.CourseEditMapper;
import rest_exam.mappers.course_mapper.CourseViewMapper;
import rest_exam.models.Company;
import rest_exam.models.Course;
import rest_exam.repositories.CompanyRepository;
import rest_exam.repositories.CourseRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CourseService {

    private final CourseRepository repository;
    private final CourseEditMapper editMapper;
    private final CourseViewMapper viewMapper;
    private final CompanyRepository companyRepository;

    public CourseResponse create(Long id,CourseRequest courseRequest){
        Company company = companyRepository.findById(id)
                .orElseThrow(()-> new CourseNorFoundException(id));
        Course course = editMapper.create(courseRequest);
        course.setCompany(company);
        repository.save(course);
        return viewMapper.viewCourse(course);
    }

//    private Company setCourseForCompany(Long companyId){
//        return companyRepository.findById(companyId)
//                .orElseThrow(()-> new CompanyNotFoundException(companyId));
//    }

    public CourseResponse update(Long id,CourseRequest courseRequest){
        Course course = repository.findById(id)
                .orElseThrow(()-> new CourseNorFoundException(id));
        editMapper.update(course,courseRequest);
        return viewMapper.viewCourse(repository.save(course));
    }

//    private Course getCourseById(Long courseId){
//        return repository.findById(courseId)
//                .orElseThrow(()-> new CourseNorFoundException(courseId));
//    }

    public CourseResponse findById(Long id){
        Course course = repository.findById(id)
                .orElseThrow(()-> new CourseNorFoundException(id));;
        return viewMapper.viewCourse(course);
    }

    public ResponseDelete deleteById (Long id ){
        boolean exist = repository.existsById(id);
        if(!exist){
            throw new CompanyNotFoundException(id);
        }
        repository.deleteById(id);
        return new ResponseDelete(
                "DELETED",
                "Successfully deleted course");
    }

    public List<CourseResponse> getAllCourse(){
        return viewMapper.view(repository.findAll());
    }

    public CourseResponseView getAllCoursesPagination(String text, int page, int size){
        CourseResponseView responseView = new CourseResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text,pageable)));
        return responseView;
    }

    public List<CourseResponse> view(List<Course> courses){
        List<CourseResponse> responses = new ArrayList<>();
        for(Course course : courses){
            responses.add(viewMapper.viewCourse(course));
        }
        return responses ;
    }

    public List<Course> search(String name, Pageable pageable){
        String text = name == null ?"":name;
        return repository.searchAndPagination(text.toUpperCase(),pageable);
    }

}
