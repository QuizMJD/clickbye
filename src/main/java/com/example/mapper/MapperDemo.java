package com.example.mapper;

import com.example.mapper.dto.UserDto;
import com.example.mapper.entity.User;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MapperDemo {

    public static void main(String[] args) {
        // Tạo mapper
        UserMapper mapper = Mappers.getMapper(UserMapper.class);
        
        // Tạo entity để test
        User user = User.builder()
                .id(1L)
                .firstName("Nguyễn")
                .lastName("Văn A")
                .email("NGUYENVANA@example.com")
                .birthDate(LocalDate.of(1990, 5, 15))
                .phoneNumber("0987654321")
                .salary(15000000)
                .active(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(null) // Cố tình để null để test @BeforeMapping
                .build();

        System.out.println("===== 1. Map User -> UserDto =====");
        System.out.println("User: " + user);
        
        UserDto userDto = mapper.userToUserDto(user);
        System.out.println("\nUserDto: " + userDto);
        
        System.out.println("\n===== 2. Map UserDto -> User =====");
        User mappedUser = mapper.userDtoToUser(userDto);
        System.out.println("Mapped User: " + mappedUser);
        
        System.out.println("\n===== 3. Update User từ UserDto =====");
        UserDto updateDto = UserDto.builder()
                .id(1L)
                .email("updated@example.com")
                .salaryAmount(20000000.0)
                .isActive(false)
                .build();
                
        System.out.println("Update DTO: " + updateDto);
        System.out.println("User trước khi update: " + user);
        
        mapper.updateUserFromDto(updateDto, user);
        System.out.println("\nUser sau khi update: " + user);
    }
} 