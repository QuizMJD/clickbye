package com.example.mapper;

import com.example.mapper.config.MappingConfig;
import com.example.mapper.dto.UserDto;
import com.example.mapper.entity.User;
import com.example.mapper.util.DateUtil;
import com.example.mapper.util.StringUtil;
import org.mapstruct.*;

@Mapper(
    config = MappingConfig.class,
    uses = {DateUtil.class, StringUtil.class},
    imports = {StringUtil.class}
)
public interface UserMapper {

    // Chuyển đổi từ User sang UserDto với các ánh xạ chi tiết
    @Mapping(target = "fullName", expression = "java(StringUtil.formatFullName(user.getFirstName(), user.getLastName()))")
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "formatDate")
    @Mapping(target = "salaryAmount", source = "salary")
    @Mapping(target = "isActive", source = "active")
    @Mapping(target = "status", expression = "java(StringUtil.getStatusDescription(user.isActive()))")
    @Mapping(target = "createdDate", source = "createdAt", qualifiedByName = "formatDateTime")
    UserDto userToUserDto(User user);
    
    // Chuyển đổi từ UserDto sang User với ánh xạ ngược
    @InheritInverseConfiguration
    @Mapping(target = "firstName", ignore = true)  // Bỏ qua vì không có thông tin
    @Mapping(target = "lastName", ignore = true)   // Bỏ qua vì không có thông tin
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "parseDate")
    @Mapping(target = "createdAt", source = "createdDate", qualifiedByName = "parseDateTime")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    User userDtoToUser(UserDto userDto);
    
    // Cập nhật một đối tượng User từ UserDto
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "birthDate", source = "birthDate", qualifiedByName = "parseDate")
    @Mapping(target = "salary", source = "salaryAmount")
    @Mapping(target = "active", source = "isActive")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    void updateUserFromDto(UserDto userDto, @MappingTarget User user);
    
    // Xử lý trước khi chuyển đổi từ User sang UserDto
    @BeforeMapping
    default void beforeUserToUserDto(User user) {
        if (user.getCreatedAt() == null) {
            user.setCreatedAt(java.time.LocalDateTime.now());
        }
        if (user.getUpdatedAt() == null) {
            user.setUpdatedAt(java.time.LocalDateTime.now());
        }
    }
    
    // Xử lý sau khi chuyển đổi từ User sang UserDto
    @AfterMapping
    default void afterUserToUserDto(User user, @MappingTarget UserDto userDto) {
        if (userDto.getEmail() != null) {
            userDto.setEmail(userDto.getEmail().toLowerCase());
        }
    }
} 