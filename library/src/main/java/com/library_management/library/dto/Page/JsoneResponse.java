package com.library_management.library.dto.Page;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class JsoneResponse {
    public static <T> ResponseEntity<ApiResponse<T>> ok(T t) {
        return ResponseEntity.ok(ApiResponse.<T>builder().
                code(HttpStatus.OK.value()).
                data(t)
                .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T t) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.<T>builder()
                        .code(HttpStatus.CREATED.value())
                        .data(t)
                        .build());
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent() {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    public static <T> ResponseEntity<ApiResponse<T>> notFound(T t) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(ApiResponse.<T>builder()
                        .code(HttpStatus.NOT_FOUND.value())
                        .message(t.toString())
                        .build());
    }
}
