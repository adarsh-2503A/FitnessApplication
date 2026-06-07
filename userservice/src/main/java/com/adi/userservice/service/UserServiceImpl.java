package com.adi.userservice.service;

import com.adi.userservice.DAO.UserRepository;
import com.adi.userservice.DTO.UserDTO;
import com.adi.userservice.config.MyCustomException;
import com.adi.userservice.model.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.Optional;

@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final ObjectMapper objectMapper=new ObjectMapper();
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDTO getUser(String userId) {
        Optional<User> opUser=userRepository.findById(userId);
        User user=opUser.orElseThrow(()->new MyCustomException("userservice.user.notfound"));
        return objectMapper.convertValue(user,UserDTO.class);
    }

    @Override
    public UserDTO registerUser(UserDTO userDTO) {
        if(userRepository.findByEmail(userDTO.getEmail()).isPresent())
            throw new MyCustomException("userservice.email.exists");
        User user=objectMapper.convertValue(userDTO,User.class);
        User user1=userRepository.save(user);
        return objectMapper.convertValue(user1,UserDTO.class);
    }
}
