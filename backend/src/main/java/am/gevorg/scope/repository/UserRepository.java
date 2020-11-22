package am.gevorg.scope.repository;

import am.gevorg.scope.model.User;
import am.gevorg.scope.model.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String s);

    Optional<User> findByEmailAndPassword(String email, String password);

    List<User> findAllByRole(Role role);

    boolean existsByEmail(String email);

}
