package ueg.tc.fluencee.exceptions.advice;

import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ueg.tc.fluencee.exceptions.service.ServiceErro;

public class ControllerAdviceApi {
    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ServiceErro> resourceNotFound(ServiceException e, HttpServletRequest request) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ServiceErro err = new ServiceErro(String.valueOf(status), e.getMessage());
        return ResponseEntity.status(status).body(err);
    }
}
