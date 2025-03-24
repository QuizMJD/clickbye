package vn.hub.clickbye.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import org.springframework.util.ObjectUtils;
import vn.hub.clickbye.controller.errors.EmailExistException;
import vn.hub.clickbye.controller.errors.FieldValidationException;
import vn.hub.clickbye.integration.storage.MinioService;
import vn.hub.clickbye.integration.storage.model.UploadFileAgrs;
import vn.hub.clickbye.repository.UserRepository;
import vn.hub.clickbye.service.UserService;
import vn.hub.clickbye.service.dto.UserDTO;
import vn.hub.clickbye.common.Constant;
import vn.hub.clickbye.service.mapper.UserMapper;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MinioService minioService;
    private final UserMapper userMapper;
    @Override
    public List<UserDTO> findAll() {
        log.info("find All Users");
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO).toList();
    }
    @Override
    public UserDTO getUser(final Long id) {

        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() -> new RuntimeException(Constant.USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public UserDTO addUser(UserDTO dto) {
        final var errors = new HashMap<String, Object>();
        validateUser(dto);

        if (userRepository.existsByEmailOrUsername(dto.getEmail(), dto.getUsername())) {
            throw new EmailExistException("Email or Username already exist!");
        }

        final var entity = userMapper.toEntity(dto);
        
        // Chỉ upload ảnh nếu có
        if (dto.getAvatar() != null && !dto.getAvatar().isEmpty()) {
            final var avatarUrl = minioService.upload(
                    UploadFileAgrs.builder()
                            .file(dto.getAvatar())
                            .path("/users/avatar")
                            .build()
            );
            entity.setAvatarUrl(avatarUrl);
        }
        
        return userMapper.toDTO(userRepository.save(entity));
    }

    private void validateUser(final UserDTO dto) {
        final var errors = new HashMap<String, Object>();

//        // Validate field
//        if (ObjectUtils.isEmpty(dto.getUsername())) {
//            errors.put("username", "Username is required!");
//        }
//
//        if (ObjectUtils.isEmpty(dto.getEmail())) {
//            errors.put("email", "Email is required!");
//        }

        if (ObjectUtils.isEmpty(dto.getAvatar()) || dto.getAvatar().getSize() == 0) {
            errors.put("avatar", "Avatar is required!");
        }

        if (!errors.isEmpty()) {
            throw new FieldValidationException(HttpStatus.BAD_REQUEST.getReasonPhrase(), errors);
        }
    }


    @Override
    public UserDTO update(final UserDTO dto, final Long id) {
        final var userExist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Constant.USER_NOT_FOUND_MESSAGE));
        userExist.setEmail(dto.getEmail());
        userExist.setFirstName(dto.getFirstName());
        userExist.setLastName(dto.getLastName());
        userExist.setPhone(dto.getPhone());
        userExist.setId(dto.getId());
        userExist.setAddress(dto.getAddress());
        return userMapper.toDTO(userRepository.save(userExist));
    }

    @Override
    public void delete(final Long id) {
        final var userExist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Constant.USER_NOT_FOUND_MESSAGE));
        userRepository.delete(userExist);
    }
}
