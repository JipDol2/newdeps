package lotte.newdevps.common.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommonResponseEntity<T> {

    private String code;
    private String message;
    private T data;
    private int size;

    @Builder
    public CommonResponseEntity(String code, String message, T data, int size) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.size = size;
    }

    public static <T> CommonResponseEntity toResponseEntity(ResponseType type,T data,int size){
        return CommonResponseEntity.builder()
                .code(type.getCode())
                .message(type.getMessage())
                .data(data)
                .size(size)
                .build();
    }
}
