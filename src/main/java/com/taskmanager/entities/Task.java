package com.taskmanager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity @Table(name = "tasks")
@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class Task {
    @Id @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotNull @NotEmpty @Size(min = 3, max = 100)
    private String title;

    @Size(max = 255)
    private String description;

    @Enumerated(EnumType.STRING)
    private StatusTask status;

    private LocalDate dueDate;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    private User user;
}
