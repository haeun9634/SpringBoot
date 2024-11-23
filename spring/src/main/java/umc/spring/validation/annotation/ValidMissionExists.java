package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MissionExistsValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MissionExistsValidator.class)
public @interface ValidMissionExists {

    String message() default "해당 미션을 찾을 수 없습니다."; // 기본 에러 메시지

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}