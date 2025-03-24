package vn.hub.clickbye.service;

import vn.hub.clickbye.service.dto.UserDTO;

import java.util.List;

public interface UserService {


    List<UserDTO> findAll();

    UserDTO getUser(Long id);

    UserDTO addUser(UserDTO dto);

    UserDTO update(UserDTO dto, final Long id);

    void delete(Long id);
}
