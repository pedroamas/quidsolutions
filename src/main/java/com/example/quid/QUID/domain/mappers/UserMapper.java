package com.example.quid.QUID.domain.mappers;
import com.example.quid.QUID.domain.dtos.UserDTO;
import com.example.quid.QUID.domain.entities.User;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Component("userMapper")
public class UserMapper {
    public UserDTO userToUserDto(User user){
        UserDTO userDTO=new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setName(user.getName());
        userDTO.setEmail(user.getEmail());
        userDTO.setPassword(user.getPassword());
        userDTO.setCreatedAt(user.getCreatedAt());
        return userDTO;
    };

    public User userDtoToUser(UserDTO userDTO){
        User user=new User();
        user.setId(userDTO.getId());
        user.setName(userDTO.getName());
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
        user.setCreatedAt(userDTO.getCreatedAt());
        return user;
    };
}
