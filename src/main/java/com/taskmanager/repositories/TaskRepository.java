package com.taskmanager.repositories;

import com.taskmanager.entities.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, String> {
    @Query(value = "select t from Task t where t.id = :idTask and t.user.id = :idUser")
    Optional<Task> getDetailsTask(@Param("idTask") String idTask, @Param("idUser") String idUser);

    Page<Task> findAllByUserId(String idUser, Pageable pageable);

    Boolean existsByIdAndUserId(String idTask, String idUser);

    String id(String id);
}
