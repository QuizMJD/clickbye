package vn.hub.clickbye.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import vn.hub.clickbye.service.dto.UserDTO;
import vn.hub.clickbye.service.dto.response.Response;

import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @GetMapping
    Response<List<UserDTO>> users();

    @GetMapping("/{id}")
    Response<UserDTO> getUser(@PathVariable Long id);

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    Response<UserDTO> createUser( @ModelAttribute final UserDTO userDto);

    @PostMapping("/{id}")
    UserDTO updateUser(@PathVariable final Long id ,@RequestBody UserDTO userDTO);

    @PutMapping("/{id}")
    Response<Void> deleteUser(@PathVariable Long id);

}
