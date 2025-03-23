package vn.hub.clickbye.common.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation để validate loại file upload
 * 
 * Ví dụ sử dụng:
 * <pre>
 * @FileType(
 *     allowedTypes = {"audio/mpeg"}, 
 *     message = "Invalid audio format. Only MP3 files are allowed"
 * )
 * private MultipartFile audioFile;
 * 
 * @FileType(
 *     allowedTypes = {"video/mp4"}, 
 *     message = "Invalid video format. Only MP4 files are allowed"
 * )
 * private MultipartFile videoFile;
 * </pre>
 * 
 * Các MIME type phổ biến:
 * - MP3: audio/mpeg
 * - MP4: video/mp4
 * - JPG: image/jpeg
 * - PNG: image/png
 */
@Documented
@Constraint(validatedBy = FileTypeValidator.class)
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileType {
    /**
     * Thông báo lỗi khi file không đúng định dạng
     */
    String message() default "Invalid file type";

    /**
     * Mảng các MIME type được phép
     */
    String[] allowedTypes() default {};

    /**
     * Nhóm validation
     */
    Class<?>[] groups() default {};

    /**
     * Thông tin bổ sung
     */
    Class<? extends Payload>[] payload() default {};
} 