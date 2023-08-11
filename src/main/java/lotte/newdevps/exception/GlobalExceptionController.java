package lotte.newdevps.exception;

import lotte.newdevps.exception.common.ErrorResponse;
import lotte.newdevps.exception.common.ErrorType;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler({NewdevpsException.class})
    public ResponseEntity<Object> handlerNewdevpsException(NewdevpsException ex,WebRequest request){
        ErrorResponse errorResponse = ErrorResponse.of(ex.getErrorType());
        return handleExceptionInternal(ex,errorResponse, null , HttpStatus.INTERNAL_SERVER_ERROR,request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        FieldError fieldError = ex.getFieldError();
        String code = fieldError.getDefaultMessage();

        ErrorType errorType = ErrorType.of(code);
        ErrorResponse errorResponse = ErrorResponse.of(errorType);
        return handleExceptionInternal(ex, errorResponse, headers, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return super.handleExceptionInternal(ex, body, headers, statusCode, request);
    }
}
