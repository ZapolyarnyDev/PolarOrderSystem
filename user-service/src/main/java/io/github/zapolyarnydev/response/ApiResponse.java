package io.github.zapolyarnydev.response;

public record ApiResponse<T>(boolean success, String message, T data) {
}
