package rest_exam.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.group_dto.GroupRequest;
import rest_exam.dto.group_dto.GroupResponse;
import rest_exam.dto.group_dto.GroupResponseView;
import rest_exam.exeptions.not_found.CompanyNotFoundException;
import rest_exam.exeptions.not_found.GroupNotFoundException;
import rest_exam.mappers.group_mapper.GroupEditMapper;
import rest_exam.mappers.group_mapper.GroupViewMapper;
import rest_exam.models.Course;
import rest_exam.models.Group;
import rest_exam.repositories.CourseRepository;
import rest_exam.repositories.GroupRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository repository;
    private final GroupEditMapper editMapper;
    private final GroupViewMapper viewMapper;
    private final CourseRepository courseRepository;

    public GroupResponse create(GroupRequest groupRequest){
        Group group = editMapper.create(groupRequest);
        group.setCourses(setGroupForCourse(groupRequest.getCourseId()));
        repository.save(group);
        return viewMapper.viewGroup(group);
    }

    private List<Course> setGroupForCourse(List<Long> courseId){
        List<Course> courses = new ArrayList<>();
        for(Long course : courseId){
            courses.add(courseRepository.findById(course)
                    .orElseThrow(()-> new GroupNotFoundException(courseId)));
        }
        return courses;
    }

    public GroupResponse update(Long id,GroupRequest groupRequest){
        Group group = getGroupById(id);
        editMapper.update(group,groupRequest);
        return viewMapper.viewGroup(repository.save(group));
    }

     private Group getGroupById(Long id){
        return repository.findById(id)
                .orElseThrow(()-> new GroupNotFoundException(id));
     }

    public GroupResponse findById(Long id){
        Group group = getGroupById(id);
        return viewMapper.viewGroup(group);
    }

    public ResponseDelete deleteById (Long id ){
        boolean exist = repository.existsById(id);
        if(!exist){
            throw new CompanyNotFoundException(id);
        }
        repository.deleteById(id);
        return new ResponseDelete(
                "DELETED",
                "Successfully deleted group");
    }

    public List<GroupResponse> getAllCourse(){
        return viewMapper.view(repository.findAll());
    }

    public GroupResponseView getAllGroupPagination(String text, int page, int size){
        GroupResponseView responseView = new GroupResponseView();
        Pageable pageable = PageRequest.of(page, size);
        responseView.setResponses(view(search(text,pageable)));
        return responseView;
    }

    public List<GroupResponse> view(List<Group> groups){
        List<GroupResponse> responses = new ArrayList<>();
        for(Group group : groups){
            responses.add(viewMapper.viewGroup(group));
        }
        return responses ;
    }

    public List<Group> search(String name, Pageable pageable){
        String text = name == null ?"":name;
        return repository.searchAndPagination(text.toUpperCase(),pageable);
    }
}
