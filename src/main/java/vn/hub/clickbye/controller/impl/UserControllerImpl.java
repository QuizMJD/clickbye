package vn.hub.clickbye.controller.impl;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import vn.hub.clickbye.controller.UserController;
import vn.hub.clickbye.service.UserService;
import vn.hub.clickbye.service.dto.UserDTO;
import vn.hub.clickbye.service.dto.response.Response;

import java.util.List;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {
    private final UserService userService;

    @Override
    public Response<List<UserDTO>> users() {
        return Response.ok(userService.findAll());
    }

    @Override
    public Response<UserDTO> getUser(Long id) {
        return Response.ok(userService.getUser(id));
    }

    @Override
    public Response<UserDTO> createUser(@Valid UserDTO dto) {
        return Response.created(userService.addUser(dto));
    }

    @Override
    public UserDTO updateUser(final Long id, UserDTO dto) {
        return userService.update(dto,id);
    }

    @Override
    public Response<Void> deleteUser(Long id) {
        userService.delete(id);
        return Response.noContent();
    }

}
