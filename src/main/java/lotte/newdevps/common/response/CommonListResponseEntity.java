package lotte.newdevps.common.response;

import lombok.Builder;
import lombok.Getter;
import lotte.newdevps.dto.post.response.PostDTO;

import java.util.List;

@Getter
public class CommonListResponseEntity<T>{
    private String code;
    private String message;
    private List<T> data;
    private int size;

    @Builder
    public CommonListResponseEntity(String code, String message, List<T> data, int size) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.size = size;
    }

    public static <T> CommonListResponseEntity toListResponseEntity(ResponseType type,List<T> data,int size){
        return CommonListResponseEntity.<T>builder()
                .code(type.getCode())
                .message(type.getMessage())
                .data(data)
                .size(size)
                .build();
    }
}
