package nl.yacht.molvenov3.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_ACCEPTABLE, reason="There is already a table with this table number")
public class SameTableNumberException extends RuntimeException{
}
