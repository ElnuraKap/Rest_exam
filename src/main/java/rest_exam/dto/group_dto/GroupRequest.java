package rest_exam.dto.group_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GroupRequest {

    private String groupName;

    private String dateOfStart;

    private String dateOfFinish;

    private List<Long> courseId;
}
