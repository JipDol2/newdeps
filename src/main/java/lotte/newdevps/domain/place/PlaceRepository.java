package lotte.newdevps.domain.place;

import lotte.newdevps.dto.place.response.PlaceDTO;
import lotte.newdevps.dto.place.response.PlaceDTOInterface;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PlaceRepository extends JpaRepository<Place,Long> {

    @Query(value = "SELECT p.id,p.latitude,p.longitude,p.category,p.placeName,p.address,p.categoryName,p.starRating,p.detailUrl,p.imageUrl,CASE WHEN b.id IS NOT NULL THEN 'Y' ELSE 'N' END AS BookmarkStatus" +
            " FROM Place p LEFT JOIN (SELECT b.id,b.place_id FROM Bookmark b WHERE b.user_id = :loginId)B ON p.id = b.place_id",nativeQuery = true)
    List<PlaceDTOInterface> findByAllPlaceDTO(@Param("loginId")Long loginId);
}