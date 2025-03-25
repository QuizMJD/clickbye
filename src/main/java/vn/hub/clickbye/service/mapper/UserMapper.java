package vn.hub.clickbye.service.mapper;

import org.mapstruct.*;
import vn.hub.clickbye.entity.UserEntity;
import vn.hub.clickbye.service.dto.UserDTO;

/**
 * Interface mapper để chuyển đổi giữa UserEntity và UserDTO
 * Sử dụng MapStruct để tự động sinh code chuyển đổi
 */
@Mapper(componentModel = "spring") // Tạo instance của mapper dưới dạng Spring Bean
public interface UserMapper {
    
    /**
     * Chuyển đổi từ UserEntity sang UserDTO
     * Các ánh xạ đặc biệt:
     * - fullName: được tạo bằng cách ghép firstName và lastName
     * - lastName: được thêm tiền tố "Ông" qua phương thức mapLastName
     *
     * @param userEntity đối tượng entity nguồn
     * @return đối tượng DTO đích
     */
    @Mapping(
        target = "fullName", 
        expression = "java(\"%s %s\".formatted(userEntity.getFirstName(), userEntity.getLastName()))"
    ) // Ghép firstName và lastName thành fullName
    @Mapping(
        target = "lastName",
        source = "lastName",
        qualifiedByName = "mapLastName"
    ) // Ánh xạ lastName qua phương thức tùy chỉnh
    UserDTO toDTO(UserEntity userEntity);

    /**
     * Chuyển đổi từ UserDTO sang UserEntity
     * MapStruct sẽ tự động ánh xạ các trường có tên giống nhau
     *
     * @param userDTO đối tượng DTO nguồn
     * @return đối tượng entity đích
     */
    UserEntity toEntity(UserDTO userDTO);

    /**
     * Phương thức tùy chỉnh để xử lý lastName
     * Thêm tiền tố "Ông" vào trước lastName
     *
     * @param lastName họ của người dùng
     * @return họ đã được thêm tiền tố
     */
    @Named("mapLastName")
    default String mapLastName(String lastName) {
        return "Ông " + lastName;
    }

    /**
     * Xử lý sau khi mapping hoàn tất
     * Set lại fullName bằng cách ghép firstName và lastName
     *
     * @param dto đối tượng DTO đích
     * @param userEntity đối tượng entity nguồn
     */
    @AfterMapping
    default void mappingFullName(@MappingTarget UserDTO dto, UserEntity userEntity) {
        dto.setFullName(userEntity.getFirstName() + " " + userEntity.getLastName());
    }
}
