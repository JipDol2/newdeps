package lotte.newdevps.domain.place;

import lombok.Getter;

@Getter
public enum PlaceCategory {

    FOOD("맛집"),
    ACCOMMODATION("숙박"),
    POPULAR("핫플")
    ;

    private String name;

    PlaceCategory(String name) {
        this.name = name;
    }
}
