package com.taskmanager.repositories;

import com.taskmanager.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Optional<Users> findByName(String name);
    Boolean existsByEmail(String email);
}
