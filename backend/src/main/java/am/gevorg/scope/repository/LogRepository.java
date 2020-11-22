package am.gevorg.scope.repository;

import am.gevorg.scope.model.Log;
import am.gevorg.scope.model.Project;
import am.gevorg.scope.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface LogRepository extends JpaRepository<Log, Integer> {

    List<Log> findAllByCreatedBy(User user);

    Optional<Log> findByDate(Date date);

    List<Log> findAllByProjectId(int id);
}
