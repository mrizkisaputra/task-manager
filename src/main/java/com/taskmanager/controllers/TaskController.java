package com.taskmanager.controllers;

import com.taskmanager.dto.*;
import com.taskmanager.entities.StatusTask;
import com.taskmanager.entities.Task;
import com.taskmanager.entities.Users;
import com.taskmanager.exceptions.TaskNotFoundException;
import com.taskmanager.repositories.TaskRepository;
import com.taskmanager.services.TaskService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/tasks")
@Slf4j
@AllArgsConstructor
public class TaskController {
    private final TaskRepository taskRepository;
    private final TaskService taskService;

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> handleGetDetailsTask(@PathVariable String id, @AuthenticationPrincipal Users user)
            throws TaskNotFoundException {

        Task task = taskRepository.getDetailsTask(id, user.getId())
                .orElseThrow(() -> new TaskNotFoundException("task not found"));

        SimpleTaskResponse taskResponse = new SimpleTaskResponse(
                task.getId(),
                user.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus().name(),
                task.getDueDate());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("success")
                .data(taskResponse)
                .build();

        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> handleCreateTask(@RequestBody @Valid CreateTaskRequest newCreateTaskRequest,
                                                  @AuthenticationPrincipal Users users,
                                                  UriComponentsBuilder ucb) {
        Task taskEntity = Task.builder()
                .title(newCreateTaskRequest.getTitle())
                .description(newCreateTaskRequest.getDescription())
                .dueDate(LocalDate.now())
                .status(StatusTask.TODO)
                .user(users)
                .build();
        Task savedTask = taskRepository.save(taskEntity);

        URI location = ucb.path("/api/v1/tasks/{id}")
                .buildAndExpand(savedTask.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Object> handleGetAllTask(@AuthenticationPrincipal Users user, Pageable pageable) {
        Page<Task> tasks = taskRepository.findAllByUserId(user.getId(),
                PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()));

        Pagination pagination = new Pagination(tasks.getTotalElements(), tasks.getSize(), tasks.getTotalPages());
        ApiResponse apiResponse = ApiResponse.builder()
                .status(HttpStatus.OK)
                .message("success")
                .data(tasks.getContent())
                .pagination(pagination)
                .build();
        return ResponseEntity.status(apiResponse.getStatus()).body(apiResponse);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    private ResponseEntity<Void> handleUpdateTask(@PathVariable String id,
                                                  @RequestBody @Valid UpdateTaskRequest newTaskRequest,
                                                  @AuthenticationPrincipal Users user) throws TaskNotFoundException {
        taskService.update(id, user.getId(), newTaskRequest);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    private ResponseEntity<Void> handleDeleteTask(@PathVariable String id, @AuthenticationPrincipal Users user) throws TaskNotFoundException {
        taskService.delete(id, user.getId());
        return ResponseEntity.noContent().build();
    }
}
