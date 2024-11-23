package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;
import umc.spring.repository.StoreRepository.StoreRepository;
import umc.spring.domain.Store;
import umc.spring.apiPayLoad.exception.GeneralException;
import umc.spring.apiPayLoad.code.status.ErrorStatus;
import lombok.RequiredArgsConstructor;
import umc.spring.validation.annotation.ValidStoreExists;

@Component
@RequiredArgsConstructor
public class StoreExistsValidator implements ConstraintValidator<ValidStoreExists, Long> {

    private final StoreRepository storeRepository;

    @Override
    public void initialize(ValidStoreExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long storeId, ConstraintValidatorContext context) {
        boolean exists = storeRepository.findById(storeId).isPresent();
        if (!exists) {
            new GeneralException(ErrorStatus.STORE_NOT_FOUND);
        }
        return exists;
    }
}

