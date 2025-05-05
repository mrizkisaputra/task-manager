package com.taskmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity @Table(name = "users")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull
    @NotEmpty
    @Size(min = 3, max = 100)
    private String name;

    @Email
    @NotNull
    @NotEmpty
    @Column(unique = true)
    @Size(min = 3, max = 100)
    private String email;

    @NotNull
    @NotEmpty
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Task> task;
}
