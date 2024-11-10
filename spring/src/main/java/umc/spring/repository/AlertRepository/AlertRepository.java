package umc.spring.repository.AlertRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.spring.domain.Alert;


public interface AlertRepository extends JpaRepository<Alert, Long>, AlertRepositoryCustom {
}
