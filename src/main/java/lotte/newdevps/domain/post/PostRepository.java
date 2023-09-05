package lotte.newdevps.domain.post;

import lotte.newdevps.dto.post.response.PostDTOInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {

    @Query(value =
            "SELECT p.*," +
                    "CASE WHEN b.id IS NOT NULL THEN 'Y' ELSE 'N' END AS BookmarkStatus " +
            "FROM POST p LEFT JOIN (" +
                "SELECT b.id,b.post_id " +
                "FROM BOOKMARK b " +
                "WHERE b.user_id = :loginId" +
            ")b ON p.id = b.post_id",nativeQuery = true)
    List<PostDTOInterface> findByAllPostDTO(@Param("loginId")Long loginId);
}
