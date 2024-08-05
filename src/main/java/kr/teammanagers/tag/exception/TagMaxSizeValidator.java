package kr.teammanagers.tag.exception;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class TagMaxSizeValidator implements ConstraintValidator<TagMaxSize, List<?>> {

    private int maxSize;

    @Override
    public void initialize(TagMaxSize constraintAnnotation) {
        this.maxSize = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        return value == null || value.size() <= maxSize;
    }
}
