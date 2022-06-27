package rest_exam.dto.group_dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
public class GroupResponse {

    private Long id;

    private String groupName;

    private String dateOfStart;

    private String dateOfFinish;

    @CreatedDate
    private LocalDate created;

    private boolean isActive;

}
