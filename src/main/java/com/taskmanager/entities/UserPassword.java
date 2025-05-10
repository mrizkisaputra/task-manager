package com.taskmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity @Table(name = "s_password")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class UserPassword {
    @Id
    @Column(name = "id_user")
    private String id;

    @NotNull
    @NotEmpty
    private String password;

    @OneToOne() @MapsId
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private Users user;
}
