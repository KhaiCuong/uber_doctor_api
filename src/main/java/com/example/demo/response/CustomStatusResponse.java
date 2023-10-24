package com.example.demo.response;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Component
public class CustomStatusResponse<T> {
    public int status;
    public String message;
    public T data;

    public ResponseEntity<CustomStatusResponse<?>> OK200(String message) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.OK.value(), message, null);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>> OK200(String message, T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.OK.value(), message, data);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>CREATED201(String message,T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.CREATED.value(), message, data);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>BADREQUEST400(String message) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.BAD_REQUEST.value(), message, null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>BADREQUEST400(String message, T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.BAD_REQUEST.value(), message, data);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>UNAUTHORIZED401(String message) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.UNAUTHORIZED.value(), message, null);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>UNAUTHORIZED401(String message, T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.UNAUTHORIZED.value(), message, data);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>FORBIDDEN403(String message) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.FORBIDDEN.value(), message, null);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>FORBIDDEN403(String message, T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.FORBIDDEN.value(), message, data);
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>NOTFOUND404(String message) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.NOT_FOUND.value(), message, null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>NOTFOUND404(String message, T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.NOT_FOUND.value(), message, data);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>METHODNOTALLOWED405(String message) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.METHOD_NOT_ALLOWED.value(), message, null);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>METHODNOTALLOWED405(String message, T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.METHOD_NOT_ALLOWED.value(), message, data);
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
    }

    public ResponseEntity<CustomStatusResponse<?>>INTERNALSERVERERROR500(T data) {
        CustomStatusResponse<T> response = new CustomStatusResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), "An error occurred while retrieving the model.", data);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }
}