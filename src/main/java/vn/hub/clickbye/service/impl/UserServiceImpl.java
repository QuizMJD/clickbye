package vn.hub.clickbye.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import vn.hub.clickbye.entity.repository.UserRepository;
import vn.hub.clickbye.service.UserService;
import vn.hub.clickbye.service.dto.UserDTO;
import vn.hub.clickbye.common.Constant;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Override
    public List<UserDTO> findAll() {
        log.info("find All Users");
        return userRepository.findAll()
                .stream()
                .map(UserDTO::fromUser).toList();
    }
    @Override
    public UserDTO getUser(final Long id) {

        return userRepository.findById(id)
                .map(UserDTO::fromUser)
                .orElseThrow(() -> new RuntimeException(Constant.USER_NOT_FOUND_MESSAGE));
    }

    @Override
    public UserDTO addUser(UserDTO dto) {
        return UserDTO.fromUser(userRepository.save(dto.toEntity()));
    }

    @Override
    public UserDTO update(UserDTO dto, final Long id) {
        final var userExist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Constant.USER_NOT_FOUND_MESSAGE));
        userExist.setEmail(dto.getEmail());
        userExist.setFirstName(dto.getFirstName());
        userExist.setLastName(dto.getLastName());
        userExist.setPhone(dto.getPhone());
        userExist.setId(dto.getId());
        userExist.setAddress(dto.getAddress());
        return UserDTO.fromUser(userRepository.save(userExist));
    }

    @Override
    public void delete(final Long id) {
        final var userExist = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(Constant.USER_NOT_FOUND_MESSAGE));
        userRepository.delete(userExist);
    }
}
