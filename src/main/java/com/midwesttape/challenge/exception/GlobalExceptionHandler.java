package com.midwesttape.challenge.exception;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.NoSuchElementException;
import java.util.StringJoiner;
import java.util.UUID;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> handleConstraintViolationException(ConstraintViolationException ex, HttpServletRequest request) {
        StringJoiner message = new StringJoiner(",");
        ex.getConstraintViolations().forEach(constraintViolation ->  message.add(constraintViolation.getMessage()));

        ErrorResponse errorResponse = ErrorResponse.builder()
                                        .message(message.toString())
                                        .messageId(UUID.randomUUID().toString()) // this is typically for tracking purpose
                                        .errorCode(HttpStatus.BAD_REQUEST.value())
                                        .build();

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ErrorResponse> handleNoDataException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(getErrorResponse(ex, HttpStatus.NO_CONTENT.value()));
    }

    @ExceptionHandler(AuthorizationDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAuthenticationException(AuthorizationDeniedException ex) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(getErrorResponse(ex, HttpStatus.FORBIDDEN.value()));
    }

    // 500s // Rest of the exceptions come here
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        return ResponseEntity.internalServerError().body(getErrorResponse(ex, HttpStatus.INTERNAL_SERVER_ERROR.value()));
    }

    private static ErrorResponse getErrorResponse(Exception ex, int errorCode) {

        return new ErrorResponse(errorCode, UUID.randomUUID().toString(), ex.getLocalizedMessage());
    }

}
