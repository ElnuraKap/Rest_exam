package rest_exam.exeptions.not_found;

import java.text.MessageFormat;
import java.util.List;

public class CourseNorFoundException extends RuntimeException {

    public CourseNorFoundException() {
    }

    public CourseNorFoundException(Long id) {
        super(MessageFormat.format
                ("Could not find course with id : {id}",id));
    }
}
