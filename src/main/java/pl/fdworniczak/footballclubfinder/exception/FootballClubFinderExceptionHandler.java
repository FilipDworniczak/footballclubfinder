package pl.fdworniczak.footballclubfinder.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Created by filip on 03.10.19
 */

@ControllerAdvice
public class FootballClubFinderExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(FootballClubFinderException.class)
    @ResponseBody
    public ResponseEntity<ErrorDTO> handleFootballClubFinderException(FootballClubFinderException ex) {
        return new ResponseEntity<>(new ErrorDTO(HttpStatus.NOT_FOUND.toString(), ex.message), HttpStatus.NOT_FOUND);
    }

    public class ErrorDTO {
        public String status;
        public String message;

        public ErrorDTO(final String status, final String message) {
            this.status = status;
            this.message = message;
        }
    }
}
