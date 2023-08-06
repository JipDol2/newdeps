package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.BookmarkService;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.bookmark.BookmarkSaveDTO;
import lotte.newdevps.ui.auth.Authentication;
import lotte.newdevps.ui.auth.NoAuth;
import lotte.newdevps.ui.auth.UserSession;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    /**
     * 북마크 저장 (B001)
     */
    @NoAuth
    @PostMapping("/{placeId}")
    public CommonResponseEntity<?> saveBookmark(@Authentication UserSession session,@PathVariable Long placeId,@RequestBody BookmarkSaveDTO bookmarkDTO){
        bookmarkService.saveBookmark(session,placeId,bookmarkDTO);
        return CommonResponseEntity.toResponseEntity(ResponseType.B001,null,0);
    }

    /**
     * 북마크 조회 (B002)
     */
    @GetMapping("/all")
    public CommonResponseEntity<?> findByBookmarkList(@Authentication UserSession session){
        return null;
    }
}
