package com.taskmanager.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CreateUserRequest {
    @NotEmpty @NotNull @Size(min = 3, max = 100)
    private String name;

    @NotEmpty @NotNull @Email @Size(min = 3, max = 100)
    private String email;

    @NotEmpty @NotNull
    private String password;
}
