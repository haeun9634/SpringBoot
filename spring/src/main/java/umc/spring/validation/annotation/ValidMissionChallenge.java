package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.MissionChallengeValidator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MissionChallengeValidator.class)
public @interface ValidMissionChallenge {

    String message() default "이미 도전 중인 미션입니다."; // 검증 실패 메시지

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    // userId를 속성으로 추가
    String userId() default "";
}

