package ueg.tc.fluencee.exception.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ueg.tc.fluencee.exception.ServiceException;
import ueg.tc.fluencee.exception.SistemaMessageCode;

@ControllerAdvice
public class ControllerAdviceApi {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<String> resourceNotFound(ServiceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.valueOf(e.getStatus());
        return ResponseEntity.status(status).body(e.getCode());
    }
}
