package lotte.newdevps.dto.bookmark.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.bookmark.BookmarkType;

@Getter
@NoArgsConstructor
public class BookmarkListDTO {

    private BookmarkType bookmarkType;
    private Long size;

    public BookmarkListDTO(BookmarkType bookmarkType, Long size) {
        this.bookmarkType = bookmarkType;
        this.size = size;
    }
}
