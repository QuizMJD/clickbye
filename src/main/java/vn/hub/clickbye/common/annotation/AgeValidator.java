package vn.hub.clickbye.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator cho annotation @AgeValid
 * 
 * Kiểm tra:
 * 1. Tuổi không được null
 * 2. Tuổi phải lớn hơn 18
 */
public class AgeValidator implements ConstraintValidator<AgeValid, Integer> {
    
    @Override
    public void initialize(AgeValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(Integer age, ConstraintValidatorContext context) {
        if (age == null) {
            return false;
        }
        return age >= 18;
    }
} 