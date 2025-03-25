package com.example.mapper.config;

import org.mapstruct.MapperConfig;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

/**
 * Cấu hình chung cho tất cả các mapper trong ứng dụng
 * Interface này được sử dụng làm cấu hình cơ sở cho các mapper khác thông qua @Mapper(config = MappingConfig.class)
 */
@MapperConfig(
    // Tạo mapper dưới dạng Spring Bean, có thể inject vào các class khác
    componentModel = "spring",
    
    // Bỏ qua các trường không được ánh xạ mà không báo lỗi
    // Hữu ích khi DTO và Entity có cấu trúc khác nhau nhiều
    unmappedTargetPolicy = ReportingPolicy.IGNORE,
    
    // Bỏ qua các trường có giá trị null khi mapping
    // Giúp tránh ghi đè giá trị hiện có bằng null
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface MappingConfig {
    // Interface này chỉ dùng để cấu hình
    // Không cần định nghĩa phương thức nào
} 