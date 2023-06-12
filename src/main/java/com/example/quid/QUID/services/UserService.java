package com.example.quid.QUID.services;

import com.example.quid.QUID.domain.dtos.UserDTO;
import com.example.quid.QUID.domain.entities.User;
import com.example.quid.QUID.domain.mappers.UserMapper;
import com.example.quid.QUID.domain.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserMapper userMapper;

    public List<UserDTO> getUsers(){
        return userRepository.findAll().stream().map(userMapper::userToUserDto).collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO userDTO){
        if(userRepository.findByEmail(userDTO.getEmail())!=null){
            throw new RuntimeException("El usuario ya existe");
        }
        String base64Encoded = Base64.getEncoder().encodeToString(userDTO.getPassword().getBytes());

        userDTO.setPassword(base64Encoded);
        User user = userMapper.userDtoToUser(userDTO);
        return userMapper.userToUserDto(userRepository.save(user));
    }

    public UserDTO loginUser(UserDTO userDTO){
        String base64Encoded = Base64.getEncoder().encodeToString(userDTO.getPassword().getBytes());
        User user = userRepository.findByEmailAndPassword(userDTO.getEmail(),base64Encoded);
        if(user==null){
            return null;
        }
        return userMapper.userToUserDto(user);
    }
}
