package dev.codex.redindiansnight.User.Interfaces.web.Advice;

import dev.codex.redindiansnight.Common.Models.ErrorResponse;
import dev.codex.redindiansnight.User.Domain.Exceptions.DuplicateEmailException;
import dev.codex.redindiansnight.User.Domain.Exceptions.PermissionNotFoundException;
import dev.codex.redindiansnight.User.Domain.Exceptions.RoleNotFoundException;
import dev.codex.redindiansnight.User.Domain.Exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class UserExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "User not found",
                Map.of(
                        "message", ex.getMessage(),
                        "user id", ex.getId() != null
                                ? ex.getId().toString()
                                : ex.getEmail()
                )
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<Object> handleDuplicateEmailException(DuplicateEmailException ex) {
        final ErrorResponse response = new ErrorResponse(
                HttpStatus.CONFLICT.value(),
                "Email already exists.",
                Map.of("email", ex.getMessage())
        );

        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleRoleNotFoundException(RoleNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Role not found",
                Map.of(
                        "message", ex.getMessage(),
                        "role id", ex.getId().toString()
                )
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PermissionNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePermissionNotFoundException(PermissionNotFoundException ex) {
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Permission not found",
                Map.of(
                        "message", ex.getMessage(),
                        "permission id", ex.getId().toString()
                )
        );

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }
}
