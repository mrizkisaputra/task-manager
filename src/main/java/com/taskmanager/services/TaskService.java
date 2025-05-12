package com.taskmanager.services;

import com.taskmanager.dto.UpdateTaskRequest;
import com.taskmanager.entities.StatusTask;
import com.taskmanager.entities.Task;
import com.taskmanager.exceptions.TaskNotFoundException;
import com.taskmanager.repositories.TaskRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class TaskService {
    private TaskRepository taskRepository;

    @Transactional(rollbackFor = TaskNotFoundException.class)
    public void update(String idTask, String idUser, UpdateTaskRequest newTaskRequest) throws TaskNotFoundException {
        Task task = taskRepository.getDetailsTask(idTask, idUser)
                .orElseThrow(() -> new TaskNotFoundException("task not found"));
        task.setTitle(newTaskRequest.getTitle());
        task.setDescription(newTaskRequest.getDescription());
        task.setStatus(StatusTask.valueOf(newTaskRequest.getStatus()));
        task.setDueDate(LocalDate.parse(newTaskRequest.getDueDate()));

        taskRepository.save(task);
    }

    @Transactional(rollbackFor = {TaskNotFoundException.class})
    public void delete(String idTask, String idUser) throws TaskNotFoundException {
        Boolean exists = taskRepository.existsByIdAndUserId(idTask, idUser);
        if (!exists) {
            throw new TaskNotFoundException("task not found");
        }
        taskRepository.deleteById(idTask);
    }
}
