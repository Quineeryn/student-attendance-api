package com.skpijtk.springboot_boilerplate.exception;

import com.skpijtk.springboot_boilerplate.dto.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

        private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

        // === Handler untuk Error Validasi & Request ===

        @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<ApiResponse<Object>> handleValidationExceptions(MethodArgumentNotValidException ex) {
                Map<String, String> errors = new HashMap<>();
                ex.getBindingResult().getAllErrors().forEach((error) -> {
                        String fieldName = ((FieldError) error).getField();
                        String errorMessage = error.getDefaultMessage();
                        errors.put(fieldName, errorMessage);
                });
                ApiResponse<Object> response = new ApiResponse<>(errors, "Validasi Gagal", HttpStatus.BAD_REQUEST.value(), "Bad Request");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(MethodArgumentTypeMismatchException.class)
        public ResponseEntity<ApiResponse<Object>> handleTypeMismatchException(MethodArgumentTypeMismatchException ex) {
                String message = String.format("Parameter '%s' harusnya bertipe '%s'.", ex.getName(), ex.getRequiredType().getSimpleName());
                ApiResponse<Object> response = new ApiResponse<>(null, message, HttpStatus.BAD_REQUEST.value(), "Bad Request");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
        public ResponseEntity<ApiResponse<Object>> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex) {
                String message = "Method " + ex.getMethod() + " tidak didukung untuk endpoint ini. Method yang didukung adalah " + ex.getSupportedHttpMethods();
                ApiResponse<Object> response = new ApiResponse<>(null, message, HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed");
                return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
        }

        // === Handler untuk Error Keamanan (Spring Security) ===

        @ExceptionHandler(BadCredentialsException.class)
        public ResponseEntity<ApiResponse<Object>> handleBadCredentialsException(BadCredentialsException ex) {
                ApiResponse<Object> response = new ApiResponse<>(null, "Email atau password salah.", HttpStatus.UNAUTHORIZED.value(), "Unauthorized");
                return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }

        @ExceptionHandler(AccessDeniedException.class)
        public ResponseEntity<ApiResponse<Object>> handleAccessDeniedException(AccessDeniedException ex) {
                ApiResponse<Object> response = new ApiResponse<>(null, "Anda tidak memiliki izin untuk mengakses sumber daya ini.", HttpStatus.FORBIDDEN.value(), "Forbidden");
                return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
        }

        // === Handler untuk Error Aturan Bisnis (Custom Exceptions) ===

        @ExceptionHandler(ResourceNotFoundException.class)
        public ResponseEntity<ApiResponse<Object>> handleResourceNotFoundException(ResourceNotFoundException ex) {
                ApiResponse<Object> response = new ApiResponse<>(null, ex.getMessage(), HttpStatus.NOT_FOUND.value(), "Not Found");
                return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        @ExceptionHandler({EmailAlreadyExistsException.class, NimAlreadyExistsException.class, IllegalStateException.class})
        public ResponseEntity<ApiResponse<Object>> handleConflictExceptions(RuntimeException ex) {
                ApiResponse<Object> response = new ApiResponse<>(null, ex.getMessage(), HttpStatus.CONFLICT.value(), "Conflict");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        @ExceptionHandler({DataIntegrityException.class, DataIntegrityViolationException.class})
        public ResponseEntity<ApiResponse<Object>> handleDataIntegrityException(RuntimeException ex) {
                ApiResponse<Object> response = new ApiResponse<>(null, "Operasi tidak bisa dilanjutkan karena melanggar integritas data. (Contoh: Menghapus data yang masih direferensikan).", HttpStatus.CONFLICT.value(), "Conflict");
                return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        }

        // === Handler All ===

        @ExceptionHandler(Exception.class)
        public ResponseEntity<ApiResponse<Object>> handleGlobalException(Exception ex, WebRequest request) {
                logger.error("Terjadi kesalahan internal yang tidak tertangani: ", ex);
                ApiResponse<Object> response = new ApiResponse<>(null, "Terjadi kesalahan internal pada server. Silakan hubungi administrator.", HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal Server Error");
                return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
}