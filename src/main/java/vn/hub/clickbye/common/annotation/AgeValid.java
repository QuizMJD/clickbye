package vn.hub.clickbye.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation để validate tuổi
 * 
 * Ví dụ sử dụng:
 * <pre>
 * @AgeValid(message = "Tuổi phải lớn hơn 18")
 * private Integer age;
 * </pre>
 */
@Documented
@Constraint(validatedBy = AgeValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface AgeValid {
    String message() default "Tuổi phải lớn hơn 18";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
} 