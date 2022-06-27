package rest_exam.dto.company_dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;

@Getter
@Setter
public class CompanyResponse {

    private Long id;

    private String companyName;

    private String locatedCountry;



    @CreatedDate
    private LocalDate created;

    private boolean isActive;

}
