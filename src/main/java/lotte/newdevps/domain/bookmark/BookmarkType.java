package lotte.newdevps.domain.bookmark;

import lombok.Getter;

@Getter
public enum BookmarkType {

    RECOMMENDTAB("추천텝"),
    NEARBYTAB("내주변텝");

    private String name;

    BookmarkType(String name) {
        this.name = name;
    }
}
