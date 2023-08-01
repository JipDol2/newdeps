package lotte.newdevps.exception.common;

import lombok.Builder;
import lombok.Getter;
import lotte.newdevps.exception.NewdevpsException;

import java.util.HashMap;
import java.util.Map;

@Getter
public class ErrorResponse{
    private String code;
    private String message;
    private Map<String,String> validation;

    @Builder
    public ErrorResponse(String code, String message, Map<String, String> validation) {
        this.code = code;
        this.message = message;
        this.validation = validation == null ? new HashMap<>() : validation;
    }

    public static ErrorResponse of(ErrorType errorType){
        return ErrorResponse.builder()
                .code(errorType.getCode())
                .message(errorType.getMessage())
                .build();
    }
}
