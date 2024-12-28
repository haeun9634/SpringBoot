package umc.spring.repository.UserRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, UserRepositoryCustom {
    Optional<User> findByEmail(String email);
}
