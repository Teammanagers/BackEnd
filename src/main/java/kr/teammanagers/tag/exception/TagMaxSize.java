package kr.teammanagers.tag.exception;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TagMaxSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TagMaxSize {
    String message() default "태그는 최대 {size}개까지만 설정 가능합니다.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int size() default 3;
}
