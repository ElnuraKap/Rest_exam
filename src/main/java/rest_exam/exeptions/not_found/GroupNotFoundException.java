package rest_exam.exeptions.not_found;

import java.text.MessageFormat;
import java.util.List;

public class GroupNotFoundException extends RuntimeException {

    public GroupNotFoundException(Long id) {
        super(MessageFormat.format
                ("Could not find group with id : {id}",id));
    }

    public GroupNotFoundException(List<Long> id) {
        super(MessageFormat.format
            ("Could not find group with id : {id}",id));
    }
}
