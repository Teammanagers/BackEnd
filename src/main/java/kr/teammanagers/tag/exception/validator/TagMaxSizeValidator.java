package kr.teammanagers.tag.exception.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class TagMaxSizeValidator implements ConstraintValidator<TagMaxSize, List<String>> {

    private int maxSize;

    @Override
    public void initialize(TagMaxSize constraintAnnotation) {
        this.maxSize = constraintAnnotation.size();
    }

    @Override
    public boolean isValid(List<String> value, ConstraintValidatorContext context) {
        return value == null || value.size() <= maxSize;
    }
}
