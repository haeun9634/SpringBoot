package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.UserMissionRepository.UserMissionRepository;
import umc.spring.validation.annotation.ValidMissionChallenge;
import umc.spring.web.dto.StoreRequestDTO;

@Component
public class MissionChallengeValidator implements ConstraintValidator<ValidMissionChallenge, StoreRequestDTO.StoreMissionRequestDTO.UserMissionDTO > {

    private final UserMissionRepository userMissionRepository;

    public MissionChallengeValidator(UserMissionRepository userMissionRepository) {
        this.userMissionRepository = userMissionRepository;
    }

    @Override
    public boolean isValid(StoreRequestDTO.StoreMissionRequestDTO.UserMissionDTO missionDTO, ConstraintValidatorContext context) {
        Long userId = missionDTO.getUserId();
        Long missionId = missionDTO.getMissionId();

        // 이미 도전 중인 미션인지 검증
        return userMissionRepository.findByUserIdMissionId(userId, missionId).isEmpty(); // 미션이 없다면 valid
    }
}
