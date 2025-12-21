package com.bsse5a.EcommerceWeb.services;


import com.bsse5a.EcommerceWeb.dtos.UserRegistrationDto;
import com.bsse5a.EcommerceWeb.mappers.UserMapper;
import com.bsse5a.EcommerceWeb.models.UserEntity;
import com.bsse5a.EcommerceWeb.models.enums.Role;
import com.bsse5a.EcommerceWeb.respositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper){
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public void  registerUser(UserRegistrationDto userDto){
        UserEntity newUserEntity = userMapper.toEntity(userDto);
        userRepository.save(newUserEntity);
    }

}
