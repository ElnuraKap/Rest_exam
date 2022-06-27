package rest_exam.exeptions.not_found;

import java.text.MessageFormat;

public class CompanyNotFoundException extends RuntimeException {

    public CompanyNotFoundException() {
    }

    public CompanyNotFoundException( Long id) {
        super(MessageFormat.format("Could not find company with id : {id}",id));
    }
}
