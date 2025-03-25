package com.example.mapper.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String fullName;        // firstName + lastName
    private String email;
    private String birthDate;       // định dạng dd/MM/yyyy
    private String phoneNumber;
    private Double salaryAmount;    // map từ salary
    private Boolean isActive;       // map từ active
    private String status;          // tính toán từ active: "Hoạt động" hoặc "Không hoạt động"
    private String createdDate;     // createdAt định dạng dd/MM/yyyy HH:mm
} 