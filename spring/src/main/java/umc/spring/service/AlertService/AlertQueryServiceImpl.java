package umc.spring.service.AlertService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.spring.domain.enums.AgreeStatus;
import umc.spring.domain.enums.AlertType;
import umc.spring.repository.AlertRepository.AlertRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class AlertQueryServiceImpl implements AlertQueryService{
    private final AlertRepository alertRepository;

    // 알림 동의/비동의 상태 업데이트
    @Override
    public void updateAlertAgreement(Long userId, AlertType alertType, AgreeStatus agreeStatus) {
        alertRepository.updateUserAlertAgreement(userId, alertType, agreeStatus);
    }
}
