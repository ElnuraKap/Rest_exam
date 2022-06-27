package rest_exam.repositories;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import rest_exam.models.Company;
import rest_exam.models.Group;

import java.util.List;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query("select g from Group g where upper(g.groupName) like concat('%',:text,'%')")
    List<Group> searchAndPagination(@Param("text") String text, Pageable pageable);
}