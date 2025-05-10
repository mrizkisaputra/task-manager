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

@Entity @Table(name = "s_users")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Users {
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

    @OneToOne(mappedBy = "user")
    private UserPassword userPassword;

    @ManyToOne
    @JoinColumn(name = "id_role", referencedColumnName = "id")
    private UserRole role;

    @OneToMany(mappedBy = "user")
    private List<Task> task;
}
