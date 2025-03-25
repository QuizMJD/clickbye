package vn.hub.clickbye.service.mapper;

import org.mapstruct.*;
import vn.hub.clickbye.entity.UserEntity;
import vn.hub.clickbye.service.dto.UserDTO;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mapping(source = "firstName",target = "fullName")
//@Mapping(target = "fullName",expression = "java(userEntity.getFirstName() + userEntity.getLastName())")
    @Mapping(target = "fullName", expression = "java(\"%s %s\".formatted(userEntity.getFirstName(), userEntity.getLastName()))")
    @Mapping(target = "lastName",source ="lastName",qualifiedByName = "mapLastName")
    //    @Mapping(target = "id", ignore = true)
    UserDTO toDTO (UserEntity userEntity);
    UserEntity toEntity( UserDTO userDTO);

    @Named("mapLastName")
    default String mapLastName(String lastName){
    return "Ông "+lastName;    }
    @AfterMapping
    default void mappingFullName(@MappingTarget UserDTO dto,UserEntity userEntity){
        dto.setFullName(userEntity.getFirstName()+" "+userEntity.getLastName());

    }
}
