package lotte.newdevps.dto.post;

import lombok.Getter;

import java.util.List;

@Getter
public class ListPostResponse <T>{
    private List<T> data;

    public ListPostResponse(List<T> data) {
        this.data = data;
    }
}
