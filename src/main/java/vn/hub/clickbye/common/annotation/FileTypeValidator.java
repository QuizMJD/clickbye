package vn.hub.clickbye.common.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.web.multipart.MultipartFile;

/**
 * Validator cho annotation @FileType
 * 
 * Cách hoạt động:
 * 1. Khởi tạo validator với các thông tin từ annotation
 * 2. Kiểm tra file có null hoặc rỗng không
 * 3. Lấy content type của file
 * 4. So sánh content type với các loại được phép
 * 5. Trả về thông báo lỗi nếu không đúng định dạng
 */
public class FileTypeValidator implements ConstraintValidator<FileType, MultipartFile> {
    private String[] allowedTypes;
    private String message;

    @Override
    public void initialize(FileType constraintAnnotation) {
        this.allowedTypes = constraintAnnotation.allowedTypes();
        this.message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        if (file == null || file.isEmpty()) {
            return true; // Để @NotNull xử lý trường hợp null
        }

        String contentType = file.getContentType();
        if (contentType == null) {
            return false;
        }

        for (String type : allowedTypes) {
            if (contentType.equals(type)) {
                return true;
            }
        }

        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message)
                .addConstraintViolation();
        return false;
    }
} 