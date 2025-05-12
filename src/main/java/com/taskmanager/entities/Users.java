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
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity @Table(name = "s_users")
@Data @Builder @NoArgsConstructor @AllArgsConstructor
public class Users implements UserDetails {
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
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Task> task;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(this.role.getName());
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return this.userPassword.getPassword();
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
