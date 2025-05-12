package com.taskmanager.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data @AllArgsConstructor
public class SimpleTaskResponse {
    private String idTask;
    private String idUser;
    private String title;
    private String description;
    private String status;
    private LocalDate dueDate;
}
