package com.taskmanager;

import com.taskmanager.dto.ApiErrorResponse;
import com.taskmanager.exceptions.EmailAlreadyUsedException;
import com.taskmanager.exceptions.TaskNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@RestControllerAdvice
public class RestExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({TaskNotFoundException.class})
    private ResponseEntity<Object> handleTaskNotFound(TaskNotFoundException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }

    @ExceptionHandler({EmailAlreadyUsedException.class})
    private ResponseEntity<Object> handleEmailAlreadyUsed(EmailAlreadyUsedException ex) {
        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.CONFLICT, ex.getMessage());
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        /* mengambil validasi error di level properti/field */
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();

        /* mengambil validasi error di level class */
        List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();

        ApiErrorResponse apiErrorResponse = new ApiErrorResponse(HttpStatus.BAD_REQUEST, "Validation Failed");
        apiErrorResponse.addValidationErrors(fieldErrors);
        apiErrorResponse.addValidationError(globalErrors);
        return ResponseEntity.status(apiErrorResponse.getStatus()).body(apiErrorResponse);
    }
}
