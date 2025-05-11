package com.taskmanager.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.ConstraintViolation;
import lombok.Data;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
public class ApiErrorResponse {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime timestamp;
    private String message;
    private String debugMessage;
    private List<SubErrorResponse> subErrors;
    private String requestId;

    public ApiErrorResponse() {
        this.timestamp = LocalDateTime.now();
    }

    public ApiErrorResponse(HttpStatus status) {
        this();
        this.status = status;
    }

    public ApiErrorResponse(HttpStatus status, String message) {
        this(status);
        this.message = message;
    }

    public ApiErrorResponse(HttpStatus status, String message, Throwable ex) {
        this(status, message);
        this.debugMessage = ex.getLocalizedMessage();
    }

    public void addValidationErrors(List<FieldError> fieldErrors) {
        fieldErrors.forEach(this::addValidationError);
    }

    public void addValidationError(List<ObjectError> globalErrors) {
        globalErrors.forEach(this::addValidationError);
    }

    public void addValidationErrors(Set<ConstraintViolation<?>> violations) {
        violations.forEach(this::addValidationError);
    }

    private void addValidationError(ConstraintViolation<?> violation) {
        this.addValidationError(
                ((PathImpl) violation.getPropertyPath()).getLeafNode().asString(),
                violation.getMessage(),
                violation.getInvalidValue()
        );
    }

    private void addValidationError(FieldError fieldError) {
        this.addValidationError(
                fieldError.getField(),
                fieldError.getDefaultMessage(),
                fieldError.getRejectedValue()
        );
    }

    private void addValidationError(ObjectError objectError) {
        this.addValidationError(objectError.getObjectName(), objectError.getDefaultMessage());
    }

    private void addValidationError(String field, String message, Object rejectedValue) {
        ApiValidationErrorResponse validationErrorResponse =
                new ApiValidationErrorResponse(field, rejectedValue, message);
        this.addSubErrors(validationErrorResponse);
    }

    private void addValidationError(String field, String message) {
        ApiValidationErrorResponse validationErrorResponse = new ApiValidationErrorResponse(field);
        validationErrorResponse.setMessage(message);
        this.addSubErrors(validationErrorResponse);
    }

    private void addSubErrors(SubErrorResponse subErrorResponse) {
        if (this.subErrors == null) {
            this.subErrors = new ArrayList<>();
        }
        this.subErrors.add(subErrorResponse);
    }

}
