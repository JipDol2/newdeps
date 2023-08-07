package lotte.newdevps.domain.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookmarkRepository extends JpaRepository<Bookmark,Long> {

    @Query("SELECT b FROM Bookmark b WHERE b.user.id = :userId AND b.post IS NOT NULL")
    List<Bookmark> findAllByPostBookmark(@Param("userId") Long userId);
}
