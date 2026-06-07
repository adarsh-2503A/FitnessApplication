package com.adi.userservice.service;

import com.adi.userservice.DTO.UserDTO;

public interface UserService {
    public UserDTO getUser(String userId);
    public UserDTO registerUser(UserDTO userDTO);
}
