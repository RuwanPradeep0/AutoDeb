package backend.backend.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Global exception handler to handle specific exceptions and return meaningful responses.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handles validation errors and returns a map of field errors.
     *
     * @param ex      The MethodArgumentNotValidException exception.
     * @param request The current request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationExceptions(
            MethodArgumentNotValidException ex, WebRequest request) {

        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    /**
     * Handles ResourceNotFoundException and returns a NOT_FOUND status.
     *
     * @param ex      The ResourceNotFoundException exception.
     * @param request The current request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(
            ResourceNotFoundException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }

    /**
     * Handles ResourceAlreadyExistsException and returns a CONFLICT status.
     *
     * @param ex      The ResourceAlreadyExistsException exception.
     * @param request The current request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<Object> handleResourceAlreadyExistsException(
            ResourceAlreadyExistsException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.CONFLICT);
    }

    /**
     * Handles BadCredentialsException and returns an UNAUTHORIZED status.
     *
     * @param ex      The BadCredentialsException exception.
     * @param request The current request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> handleBadCredentialsException(
            BadCredentialsException ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("error", ex.getMessage());

        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.UNAUTHORIZED);
    }

    /**
     * Handles all other exceptions and returns an INTERNAL_SERVER_ERROR status.
     *
     * @param ex      The Exception.
     * @param request The current request.
     * @return ResponseEntity containing error details.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAllExceptions(
            Exception ex, WebRequest request) {

        Map<String, String> error = new HashMap<>();
        error.put("error", "An unexpected error occurred");

        return new ResponseEntity<>(error, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
