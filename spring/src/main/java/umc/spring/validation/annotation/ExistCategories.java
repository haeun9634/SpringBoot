package umc.spring.validation.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import umc.spring.validation.validator.CategoriesExistValidator;

import java.lang.annotation.*;

@Documented //사용자 정의 어노테이션
//파라미터로 validatedBy가 있고 CategoriesExistValidator.class를 지정
//이로써 CategoriesExistValidator라는 클래스를 통해 @ExistCategories가 붙은 대상을 검증합니다.
@Constraint(validatedBy = CategoriesExistValidator.class)
//어노테이션의 적용범위  지정 target
@Target( { ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME) //어노테이션 생명주기, 런타임, 실행 하는 동안 유효
public @interface ExistCategories {

    String message() default "해당하는 카테고리가 존재하지 않습니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

//