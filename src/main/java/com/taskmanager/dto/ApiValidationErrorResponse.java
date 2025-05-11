package com.taskmanager.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class ApiValidationErrorResponse extends SubErrorResponse {
    private String field;
    private Object rejectedValue;
    private String message;

    public ApiValidationErrorResponse(String field) {
        this.field = field;
    }

    public ApiValidationErrorResponse(String field, Object rejectedValue) {
        this(field);
        this.rejectedValue = rejectedValue;
    }

    public ApiValidationErrorResponse(String field, Object rejectedValue, String message) {
        this(field, rejectedValue);
        this.message = message;
    }
}