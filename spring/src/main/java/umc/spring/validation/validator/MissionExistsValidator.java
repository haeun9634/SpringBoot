package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.apiPayLoad.code.status.ErrorStatus;
import umc.spring.apiPayLoad.exception.GeneralException;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.validation.annotation.ValidMissionExists;
import umc.spring.validation.annotation.ValidStoreExists;

@Component
@RequiredArgsConstructor
public class MissionExistsValidator implements ConstraintValidator<ValidMissionExists, Long> {

    private final MissionRepository missionRepository;

    @Override
    public void initialize(ValidMissionExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long MissionId, ConstraintValidatorContext context) {

        return missionRepository.findById(MissionId).isPresent();
    }
}