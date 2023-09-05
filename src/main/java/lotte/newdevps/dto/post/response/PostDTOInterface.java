package lotte.newdevps.dto.post.response;

import java.time.LocalDate;

public interface PostDTOInterface {

    Long getId();
    String getContent();
    Long getViewCount();
    String getPlaceName();
    Double getLatitude();
    Double getLongitude();
    LocalDate getDateTime();
    String getAddress();
    String getCategoryName();
    Double getStarRating();
    String getBookmarkStatus();
}
