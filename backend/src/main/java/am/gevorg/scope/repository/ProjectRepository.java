package am.gevorg.scope.repository;

import am.gevorg.scope.model.Project;
import am.gevorg.scope.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProjectRepository extends JpaRepository<Project, Integer> {
    Optional<Project> findByName(String name);

    List<Project> findAllByMembersContains(User user);

    Optional<Project> findById(int id);

    boolean existsByName(String name);

}
