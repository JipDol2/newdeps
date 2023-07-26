package lotte.newdevps.exception;

import org.springframework.http.HttpStatus;

public class NewdevpsException extends RuntimeException{

    private final HttpStatus httpStatus;

    public NewdevpsException(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }
}
