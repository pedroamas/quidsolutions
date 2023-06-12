package com.example.quid.QUID.domain.dtos;

import com.example.quid.QUID.domain.commons.TaskStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@NoArgsConstructor
@Getter
@Setter
public class TaskDTO {
    private int id;
    private String title;
    private String description;
    private TaskStatus status;
    private int userId;
    private Date createdAt;
}
