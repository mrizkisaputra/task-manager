package com.taskmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "s_roles")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserRole {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @NotNull
    @Size(max = 100)
    private String id;

    @NotNull @Size(max = 100)
    @Enumerated(EnumType.STRING)
    private Role name;

    @OneToMany(mappedBy = "role")
    private List<Users> user;
}
