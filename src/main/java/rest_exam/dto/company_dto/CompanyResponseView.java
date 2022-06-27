package rest_exam.dto.company_dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyResponseView {

    List<CompanyResponse> responses;
}
