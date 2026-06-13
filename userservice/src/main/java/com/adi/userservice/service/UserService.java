package com.adi.userservice.service;

import com.adi.userservice.DTO.UserDTO;

public interface UserService {
    UserDTO getUser(String userId);
    UserDTO registerUser(UserDTO userDTO);

    Boolean existsByKeyCloakId(String keyCloakId);
}
