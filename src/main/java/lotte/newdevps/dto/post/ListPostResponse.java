package lotte.newdevps.dto.post;

import lombok.Getter;

import java.util.List;

@Getter
public class ListPostResponse <T>{
    private List<T> data;
    private int size;

    public ListPostResponse(List<T> data,int size) {
        this.data = data;
        this.size = size;
    }
}
