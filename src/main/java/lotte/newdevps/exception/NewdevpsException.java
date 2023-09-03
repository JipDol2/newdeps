package lotte.newdevps.exception;

import lombok.Getter;
import lotte.newdevps.exception.common.ErrorType;

@Getter
public class NewdevpsException extends RuntimeException{

//    private final HttpStatusCode httpStatusCode;
    private final ErrorType errorType = ErrorType.of(this.getClass());

//    public NewdevpsException(HttpStatusCode httpStatusCode) {
//        this.httpStatusCode = httpStatusCode;
//    }
}
