package kr.teammanagers.tag.exception.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static kr.teammanagers.tag.constant.TagErrorConstant.MAX_TAG_SIZE;

@Constraint(validatedBy = TagMaxSizeValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface TagMaxSize {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int size() default MAX_TAG_SIZE;
}
