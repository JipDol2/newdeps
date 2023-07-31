package lotte.newdevps.exception;

import lombok.Getter;
import lotte.newdevps.exception.response.ErrorResponse;
import lotte.newdevps.exception.response.ErrorType;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public class NewdevpsException extends RuntimeException{

//    private final HttpStatusCode httpStatusCode;
    private final ErrorType errorType = ErrorType.of(this.getClass());

//    public NewdevpsException(HttpStatusCode httpStatusCode) {
//        this.httpStatusCode = httpStatusCode;
//    }
}
