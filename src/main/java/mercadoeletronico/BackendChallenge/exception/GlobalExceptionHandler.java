package mercadoeletronico.BackendChallenge.exception;

import mercadoeletronico.BackendChallenge.dtos.error.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateCreationAttemptException.class)
    public ResponseEntity<ErrorMessage> handleDuplicateCreationAttemptException(DuplicateCreationAttemptException ex) {
        ErrorMessage errorMessage = new ErrorMessage(404, "Attempt to create a "
                + ex.getResourceName() + " that conflicts with an already existing with id = " + ex.getId());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorMessage> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorMessage errorMessage = new ErrorMessage(404, ex.getResourceName()
                + " could not be found");
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>("An error occurred: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
