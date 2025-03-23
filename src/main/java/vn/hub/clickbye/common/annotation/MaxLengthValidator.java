package vn.hub.clickbye.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * Validator cho annotation @MaxLength
 * 
 * Kiểm tra:
 * 1. Chuỗi không được null
 * 2. Độ dài chuỗi không được vượt quá max
 */
public class MaxLengthValidator implements ConstraintValidator<MaxLength, String> {
    private int max;
    private String message;
    
    @Override
    public void initialize(MaxLength constraintAnnotation) {
        this.max = constraintAnnotation.max();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Để @NotNull xử lý trường hợp null
        }
        
        if (value.length() > max) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(
                message.replace("{max}", String.valueOf(max))
            ).addConstraintViolation();
            return false;
        }
        
        return true;
    }
} 