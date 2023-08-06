package lotte.newdevps.exception.common;

import lombok.Getter;
import lotte.newdevps.exception.MethodArgumentNotValid;
import lotte.newdevps.exception.NewdevpsException;
import lotte.newdevps.exception.http.InternalException;
import lotte.newdevps.exception.place.PlaceNotFoundException;
import lotte.newdevps.exception.post.PostNotFoundException;
import lotte.newdevps.exception.user.DuplicatedUserException;
import lotte.newdevps.exception.user.UserNotFoundException;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public enum ErrorType {

    EXU001("EXU001","회원을 찾을 수 없습니다.", UserNotFoundException.class),
    EXU002("EXU002","중복된 회원이 존재합니다.", DuplicatedUserException.class),
    EXU003("EXU003","로그인 아이디는 필수값입니다.", MethodArgumentNotValid.class),
    EXU004("EXU004","닉네임은 필수값입니다.", MethodArgumentNotValid.class),

    EXP001("EXP001","게시글을 찾을 수 없습니다.",PostNotFoundException.class),

    EXL001("EXL001","추천 장소를 찾을 수 없습니다.", PlaceNotFoundException.class),

    EXX001("EXX001","정의되지 않은 예외입니다.", InternalException.class)
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

    public static ErrorType of(String code){
        return Arrays.stream(values())
                .filter(errorType -> errorType.getCode().equals(code))
                .findAny()
                .orElse(ErrorType.EXX001);
    }
}
