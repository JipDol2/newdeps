package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.BookmarkService;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.bookmark.request.BookmarkSaveDTO;
import lotte.newdevps.dto.bookmark.response.BookmarkListDTO;
import lotte.newdevps.dto.place.response.PlaceDTO;
import lotte.newdevps.dto.post.response.PostDTO;
import lotte.newdevps.ui.auth.Authentication;
import lotte.newdevps.ui.auth.NoAuth;
import lotte.newdevps.ui.auth.LoginSession;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    /**
     * 북마크 저장 (B001)
     */
    @PostMapping("/{id}")
    public CommonResponseEntity<Void> saveBookmark(@Authentication LoginSession session, @PathVariable Long id, @RequestBody BookmarkSaveDTO bookmarkDTO){
        bookmarkService.saveBookmark(session,id,bookmarkDTO);
        return CommonResponseEntity.toResponseEntity(ResponseType.B001,null,0);
    }

    /**
     * 북마크 취소(B006)
     */
    @DeleteMapping("/{id}")
    public CommonResponseEntity<Void> deleteBookmark(@Authentication LoginSession session,@PathVariable Long id, @RequestBody BookmarkSaveDTO bookmarkSaveDTO){
        bookmarkService.deleteBookmark(session,id,bookmarkSaveDTO);
        return CommonResponseEntity.toResponseEntity(ResponseType.B006,null,0);
    }

    /**
     * 북마크 조회 (B002)
     */
    @GetMapping
    public CommonListResponseEntity<?> findByRecommendBookmarkList(@Authentication LoginSession session){
        List<BookmarkListDTO> bookmarkList = bookmarkService.findByBookmarkList(session);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.B002,bookmarkList,bookmarkList.size());
    }

    /**
     * 게시글 북마크 조회 (B003)
     */
    @GetMapping("/list/post")
    public CommonListResponseEntity<PostDTO> findByNearbyBookmarkList(@Authentication LoginSession session){
        List<PostDTO> postDTOS = bookmarkService.findBookmarkPostList(session);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.B003,postDTOS,postDTOS.size());
    }

    /**
     * 추천장소 북마크 조회(B004)
     */
    @GetMapping("/list/place")
    public CommonListResponseEntity<PlaceDTO> findByRecommendPlaceList(@Authentication LoginSession session){
        List<PlaceDTO> placeDTOS = bookmarkService.findBookmarkPlaceList(session);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.B004,placeDTOS,placeDTOS.size());
    }

    /**
     * 컬렉션 조회(B005)
     */
    @GetMapping("/list")
    public CommonListResponseEntity<BookmarkListDTO> findByBookmarkList(@Authentication LoginSession session){
        List<BookmarkListDTO> bookmarkListDTOS = bookmarkService.findByBookmarkList(session);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.B005,bookmarkListDTOS,bookmarkListDTOS.size());
    }
}
