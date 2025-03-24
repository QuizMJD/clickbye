package vn.hub.clickbye.controller.impl;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import vn.hub.clickbye.controller.UserController;
import vn.hub.clickbye.service.UserService;
import vn.hub.clickbye.service.dto.UserDTO;

import java.util.List;
@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public List<UserDTO> users() {
        return userService.findAll();
    }

    @Override
    public UserDTO getUser(Long id) {
        return userService.getUser(id);
    }

    @Override
    public UserDTO createUser(UserDTO dto) {
        return userService.addUser(dto);
    }

    @Override
    public UserDTO updateUser(final Long id, UserDTO dto) {
        return null;
    }

    @Override
    public void deleteUser(Long id) {
        userService.delete(id);
    }
}
