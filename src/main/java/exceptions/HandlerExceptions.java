package exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class HandlerExceptions {
    @ExceptionHandler(LotNotFoundException.class)
    public ResponseEntity<?> lotNotFound() {

        return ResponseEntity.notFound().build();
    }
    @ExceptionHandler
    public ResponseEntity<?> lotNotFoundException(Exception exception) {
        exception.printStackTrace();
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(LotNodStartedException.class)
    public ResponseEntity<?> lotNotStarted() {

        return ResponseEntity.badRequest().build();
    }
}
