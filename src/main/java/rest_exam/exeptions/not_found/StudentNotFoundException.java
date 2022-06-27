package rest_exam.exeptions.not_found;

import java.text.MessageFormat;

public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(Long id) {
        super(MessageFormat.format
                ("Could not find student with id : {id}",id));
    }
}
