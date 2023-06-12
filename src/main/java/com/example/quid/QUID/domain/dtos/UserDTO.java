package com.example.quid.QUID.domain.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class UserDTO {
    private int id;
    private String name;
    private String email;
    private String password;
    private Date createdAt;

}
