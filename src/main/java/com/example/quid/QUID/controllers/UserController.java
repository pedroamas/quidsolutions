package com.example.quid.QUID.controllers;

import com.example.quid.QUID.domain.dtos.UserDTO;
import com.example.quid.QUID.domain.entities.User;
import com.example.quid.QUID.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> list(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(
            @RequestBody UserDTO userDTO
    ){
        return ResponseEntity.ok(userService.createUser(userDTO));
    }

    @PostMapping("login")
    public ResponseEntity<UserDTO> loginUser(
            @RequestBody UserDTO userDTO
    ){
        return ResponseEntity.ok(userService.loginUser(userDTO));
    }

}
