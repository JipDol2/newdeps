package lotte.newdevps.exception.response;

import lombok.Getter;
import lotte.newdevps.exception.NewdevpsException;
import lotte.newdevps.exception.post.PostNotFoundException;
import lotte.newdevps.exception.user.UserNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum ErrorType {

    U001("U001","회원을 찾을 수 없습니다.", UserNotFoundException.class),

    P001("P001","게시글을 찾을 수 없습니다.",PostNotFoundException.class)
    ;

    private String code;
    private String message;
    private Class<? extends NewdevpsException> classType;

    private static final Map<Class<? extends NewdevpsException>,ErrorType> codeMap = new HashMap<>();

    static{
        Arrays.stream(values())
                .forEach(errorType -> codeMap.put(errorType.getClassType(),errorType));
    }

    ErrorType(String code, String message, Class<? extends NewdevpsException> classType) {
        this.code = code;
        this.message = message;
        this.classType = classType;
    }

    public static ErrorType of(Class<? extends NewdevpsException> errorType){
        return codeMap.get(errorType);
    }
}
