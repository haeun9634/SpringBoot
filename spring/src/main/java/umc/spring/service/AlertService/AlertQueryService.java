package umc.spring.service.AlertService;

import umc.spring.domain.enums.AgreeStatus;
import umc.spring.domain.enums.AlertType;

public interface AlertQueryService {
    void updateAlertAgreement(Long userId, AlertType alertType, AgreeStatus agreeStatus);
}
