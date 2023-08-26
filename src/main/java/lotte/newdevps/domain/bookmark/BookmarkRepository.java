package lotte.newdevps.domain.bookmark;

import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.post.Post;
import lotte.newdevps.domain.user.User;
import lotte.newdevps.dto.bookmark.response.BookmarkListDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

    @Query("SELECT b FROM Bookmark b WHERE b.user.id = :userId AND b.post IS NOT NULL")
    List<Bookmark> findAllByBookmarkPost(@Param("userId") Long userId);

    @Query("SELECT b FROM Bookmark b WHERE b.user.id = :userId AND b.place IS NOT NULL")
    List<Bookmark> findAllByBookmarkPlace(@Param("userId") Long userId);

    @Query("SELECT new lotte.newdevps.dto.bookmark.response.BookmarkListDTO(b.bookmarkType,COUNT(b.id)) FROM Bookmark b WHERE b.user.id = :userId GROUP BY b.bookmarkType")
    List<BookmarkListDTO> findByBookmarkList(@Param("userId") Long userId);

    void deleteBookmarkByUserAndPlaceOrPost(@Param("user")User user, @Param("place")Place place, @Param("post")Post post);
}
