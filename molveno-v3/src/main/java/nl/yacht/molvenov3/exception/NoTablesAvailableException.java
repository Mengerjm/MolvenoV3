package nl.yacht.molvenov3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.BAD_REQUEST, reason="There are no tables available for that time period")
public class NoTablesAvailableException extends RuntimeException{
}
