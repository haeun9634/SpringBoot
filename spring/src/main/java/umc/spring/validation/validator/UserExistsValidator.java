package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import umc.spring.repository.UserRepository.UserRepository;
import umc.spring.validation.annotation.ValidUserExists;

@Component
@RequiredArgsConstructor
public class UserExistsValidator implements ConstraintValidator<ValidUserExists, Long> {

    private final UserRepository UserRepository;

    @Override
    public void initialize(ValidUserExists constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(Long UserId, ConstraintValidatorContext context) {

        return UserRepository.findById(UserId).isPresent();
    }
}