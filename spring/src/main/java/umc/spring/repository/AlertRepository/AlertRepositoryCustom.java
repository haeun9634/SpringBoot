package umc.spring.repository.AlertRepository;

import umc.spring.domain.enums.AgreeStatus;
import umc.spring.domain.enums.AlertType;

public interface AlertRepositoryCustom {
    void updateUserAlertAgreement(Long userId, AlertType alertType, AgreeStatus agreeStatus);
}
