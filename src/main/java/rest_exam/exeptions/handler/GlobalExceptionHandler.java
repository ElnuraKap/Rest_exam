package rest_exam.exeptions.handler;

import org.springframework.http.HttpStatus;
import rest_exam.exeptions.ExceptionResponse;
import rest_exam.exeptions.not_found.CompanyNotFoundException;

public class GlobalExceptionHandler {
    //500
    //400
    //401
    //403
    //404
    public ExceptionResponse handlerCompanyNotFoundException(CompanyNotFoundException e){
        return new ExceptionResponse(
                HttpStatus.NOT_FOUND,
                e.getClass().getSimpleName(),
                e.getMessage()
        );
    }
    //405
}
