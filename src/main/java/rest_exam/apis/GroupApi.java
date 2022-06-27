package rest_exam.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.group_dto.GroupRequest;
import rest_exam.dto.group_dto.GroupResponse;
import rest_exam.dto.group_dto.GroupResponseView;
import rest_exam.services.GroupService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
@PreAuthorize("hasAnyAuthority('BOSS')")
public class GroupApi {

    private final GroupService service;

    @PostMapping("/save")
    public GroupResponse create(@RequestBody GroupRequest request){
        return service.create(request);
    }

    @PutMapping("/update/by/{groupId}")
    public GroupResponse update(@PathVariable Long groupId,
                                @RequestBody GroupRequest groupRequest){
        return service.update(groupId,groupRequest);
    }

    @GetMapping("/find/by/{groupId}")
    public GroupResponse findById(@PathVariable Long groupId){
        return service.findById(groupId);
    }

    @DeleteMapping("/delete/by/{groupId}")
    public ResponseDelete delete(@PathVariable Long groupId){
        return service.deleteById(groupId);
    }

    @GetMapping
    public List<GroupResponse> getAllGroups(){
        return service.getAllCourse();
    }

    @GetMapping("/pagination")
    public GroupResponseView getAllGroup(@RequestParam(name = "text",required = false)String text,
                                         @RequestParam int page,
                                         @RequestParam int size){
        return service.getAllGroupPagination(text, page, size);
    }
}
