package vn.hub.clickbye.service.dto;

import lombok.NonNull;
import lombok.Value;
import org.springframework.web.bind.annotation.PathVariable;
import vn.hub.clickbye.entity.UserEntity;

@Value
public class UserDTO {
    Long id;
    String username;
    String firstName;
    String lastName;
    String email;
    String phone;
    String address;

    public static UserDTO fromUser(@NonNull final UserEntity entity) {
            return new UserDTO(
                    entity.getId(),
                    entity.getUsername(),
                    entity.getFirstName(),
                    entity.getLastName(),
                    entity.getAddress(),
                    entity.getEmail(),
                    entity.getPhone()
            );
    }
    public UserEntity toEntity() {
        final var entity = new UserEntity();
        entity.setId(id);
        entity.setUsername(username);
        entity.setFirstName(firstName);
        entity.setLastName(lastName);
        entity.setAddress(address);
        entity.setEmail(email);
        entity.setPhone(phone);
        return entity;

    }



}

