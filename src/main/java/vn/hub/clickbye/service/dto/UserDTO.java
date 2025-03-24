package vn.hub.clickbye.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NonNull;
import lombok.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import vn.hub.clickbye.common.annotation.AgeValid;
import vn.hub.clickbye.common.annotation.BirthDateValid;
import vn.hub.clickbye.common.annotation.MaxLength;
import vn.hub.clickbye.entity.UserEntity;

import java.time.LocalDate;

@Data
public class UserDTO {
    Long id;
    @NotBlank(message = "vui long nhap username")
    String username;
    @MaxLength(max = 50, message = "Họ không được vượt quá {max} ký tự")
    String firstName;
    String lastName;
    @NotBlank(message = "vui long nhap email")
    String email;
    String phone;
    String address;
    @AgeValid(message = "Tuổi phải lớn hơn hoặc bằng 18")
    Integer age;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @BirthDateValid(message = "Ngày sinh không được lớn hơn ngày hiện tại")
    LocalDate birthDate;
    Double salary;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    MultipartFile avatar;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    String avatarUrl;
    String fullName;


}

