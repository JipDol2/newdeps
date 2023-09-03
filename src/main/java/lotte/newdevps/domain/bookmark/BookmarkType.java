package lotte.newdevps.domain.bookmark;

import lombok.Getter;

@Getter
public enum BookmarkType {

    RECOMMEND_PLACE("추천장소"),
    POST("게시글");

    private String name;

    BookmarkType(String name) {
        this.name = name;
    }
}
