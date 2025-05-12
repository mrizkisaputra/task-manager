package com.taskmanager.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateTaskRequest {
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String title;

    @Size(max = 255)
    private String description;
}
