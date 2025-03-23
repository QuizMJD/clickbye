package vn.hub.clickbye.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation để validate ngày sinh
 * 
 * Ví dụ sử dụng:
 * <pre>
 * @BirthDateValid(message = "Ngày sinh không được lớn hơn ngày hiện tại")
 * private LocalDate birthDate;
 * </pre>
 */
@Documented
@Constraint(validatedBy = BirthDateValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BirthDateValid {
    String message() default "Ngày sinh không được lớn hơn ngày hiện tại";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 