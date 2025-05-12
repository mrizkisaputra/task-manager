package com.taskmanager.services;

import com.taskmanager.dto.CreateUserRequest;
import com.taskmanager.entities.Role;
import com.taskmanager.entities.UserPassword;
import com.taskmanager.entities.Users;
import com.taskmanager.exceptions.EmailAlreadyUsedException;
import com.taskmanager.repositories.RoleRepository;
import com.taskmanager.repositories.UserPasswordRepository;
import com.taskmanager.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {
    private UserRepository userRepository;
    private UserPasswordRepository userPasswordRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("username %s not found", username)));
    }

    @Transactional(rollbackFor = {EmailAlreadyUsedException.class})
    public void register(CreateUserRequest newCreateUserRequest) throws EmailAlreadyUsedException {
        Boolean exists = userRepository.existsByEmail(newCreateUserRequest.getEmail());
        if (exists) {
            throw new EmailAlreadyUsedException(String.format("email %s already used", newCreateUserRequest.getEmail()));
        }

        Role roleEntity = Role.builder().name("OWNER").build();
        Role saveRole = roleRepository.save(roleEntity);

        Users userEntity = Users.builder()
                .name(newCreateUserRequest.getName())
                .email(newCreateUserRequest.getEmail())
                .role(saveRole)
                .build();
        Users savedUser = userRepository.save(userEntity);

        UserPassword userPasswordEntity = UserPassword.builder()
                .password(passwordEncoder.encode(newCreateUserRequest.getPassword()))
                .user(savedUser)
                .build();
        userPasswordRepository.save(userPasswordEntity);
    }
}
