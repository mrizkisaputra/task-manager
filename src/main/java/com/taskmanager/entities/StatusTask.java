package com.taskmanager.entities;

import lombok.Getter;

public enum StatusTask {
    TODO("Task Created"), IN_PROGRESS("Task in Progress"), DONE("Task Done");

    @Getter
    private final String reasonPhrase;

    StatusTask(String reasonPhrase) {
        this.reasonPhrase = reasonPhrase;
    }
}
