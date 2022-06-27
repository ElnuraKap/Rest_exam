package rest_exam.apis;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import rest_exam.dto.ResponseDelete;
import rest_exam.dto.company_dto.CompanyRequest;
import rest_exam.dto.company_dto.CompanyResponse;
import rest_exam.dto.company_dto.CompanyResponseView;
import rest_exam.services.CompanyService;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/companies")
@PreAuthorize("hasAnyAuthority('BOSS')")
public class CompanyApi {

    private final CompanyService service;

    @PostMapping("/save")
    public CompanyResponse create(@RequestBody CompanyRequest request){
        return service.create(request );
    }

    @PutMapping("/update/by/{companyId}")
    public CompanyResponse update(@PathVariable Long companyId,
                                  @RequestBody CompanyRequest request){
        return service.update(companyId,request);
    }

    @GetMapping("/find/by/{companyId}")
    public CompanyResponse findById(@PathVariable Long companyId ){
        return service.findById(companyId);
    }

    @DeleteMapping("/delete/by/{companyId}")
    public ResponseDelete delete(@PathVariable Long companyId ){
        return service.deleteById(companyId);
    }

    @GetMapping()
    public List<CompanyResponse> getAllCompanies(){
        return service.getAllCompany();
    }

    @GetMapping("/pagination")
    public CompanyResponseView getAllCompanies(@RequestParam(name = "text",required = false)String text,
                                              @RequestParam int page,
                                              @RequestParam int size) {
        return service.getAllCompaniesPagination(text,page,size);
    }
}