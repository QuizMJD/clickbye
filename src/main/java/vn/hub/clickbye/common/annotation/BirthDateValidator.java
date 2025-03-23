package vn.hub.clickbye.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.LocalDate;

/**
 * Validator cho annotation @BirthDateValid
 * 
 * Kiểm tra:
 * 1. Ngày sinh không được null
 * 2. Ngày sinh không được lớn hơn ngày hiện tại
 */
public class BirthDateValidator implements ConstraintValidator<BirthDateValid, LocalDate> {
    
    @Override
    public void initialize(BirthDateValid constraintAnnotation) {
    }

    @Override
    public boolean isValid(LocalDate birthDate, ConstraintValidatorContext context) {
        if (birthDate == null) {
            return false;
        }
        return !birthDate.isAfter(LocalDate.now());
    }
} 