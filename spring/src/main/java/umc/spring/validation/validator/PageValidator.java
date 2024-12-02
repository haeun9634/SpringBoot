package umc.spring.validation.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import umc.spring.repository.MissionRepository.MissionRepository;
import umc.spring.validation.annotation.CheckPage;
import umc.spring.validation.annotation.ValidMissionExists;

@Component
@RequiredArgsConstructor
public class PageValidator implements ConstraintValidator<CheckPage, Integer> {

    @Override
    public boolean isValid(Integer page, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println(page);
        return page != null && page > 0 && page <=5;
    }

    public Integer adjustPage(Integer page) {
        // JPA에서 사용할 수 있도록 page 값을 -1 조정
        return page - 1;
    }

    @Override
    public void initialize(CheckPage constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }
}