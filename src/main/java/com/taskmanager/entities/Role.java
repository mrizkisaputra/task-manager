package com.taskmanager.entities;

import lombok.Getter;

public enum Role {
    ADMIN("ROLE_ADMIN"), USER("ROLE_USER");

    @Getter
    private String value;

    Role(String value) {
        this.value = value;
    }
}
