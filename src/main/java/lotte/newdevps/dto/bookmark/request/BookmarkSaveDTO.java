package lotte.newdevps.dto.bookmark.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.bookmark.BookmarkType;

@Getter
@NoArgsConstructor
public class BookmarkSaveDTO {

    private BookmarkType bookmarkType;
}
