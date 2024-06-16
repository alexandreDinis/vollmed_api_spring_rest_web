package med.voll.api.infra.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {


    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity error404Handler() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity error400Handler(MethodArgumentNotValidException ex) {

        var error = ex.getFieldErrors();

        return ResponseEntity.badRequest().body(error.stream().map(DataErrorHandler::new).toList());

    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity errorValidationRulesBussines(ValidationException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }

    private record DataErrorHandler(String name, String message) {
        public DataErrorHandler(FieldError error){
            this(error.getField(), error.getDefaultMessage());
        }
    }
}


