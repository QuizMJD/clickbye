package vn.hub.clickbye.controller;

import org.springframework.web.bind.annotation.*;
import vn.hub.clickbye.service.dto.UserDTO;

import java.util.List;

@RequestMapping("/users")
public interface UserController {

    @GetMapping
    List<UserDTO>users();

    @GetMapping("/{id}")
    UserDTO getUser(@PathVariable Long id);

    @PostMapping
    UserDTO createUser(@RequestBody UserDTO userDTO);

    @PostMapping("/{id}")
    UserDTO updateUser(@PathVariable final Long id ,@RequestBody UserDTO userDTO);

    @PutMapping("/{id}")
    void deleteUser(@PathVariable Long id);

}
