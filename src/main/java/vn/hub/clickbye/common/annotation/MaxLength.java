package vn.hub.clickbye.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation để validate độ dài chuỗi
 * 
 * Ví dụ sử dụng:
 * <pre>
 * @MaxLength(max = 50, message = "Độ dài tối đa là 50 ký tự")
 * private String firstName;
 * </pre>
 */
@Documented
@Constraint(validatedBy = MaxLengthValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MaxLength {
    String message() default "Độ dài không được vượt quá {max} ký tự";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    int max() default Integer.MAX_VALUE;
} 