package backend.backend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown when attempting to create a resource that already exists.
 */
@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {

    /**
     * Constructs a new ResourceAlreadyExistsException with the specified detail message.
     *
     * @param message Detailed message explaining the exception.
     */
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructs a new ResourceAlreadyExistsException with the specified detail message and cause.
     *
     * @param message Detailed message explaining the exception.
     * @param cause   The cause of the exception.
     */
    public ResourceAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
