package lotte.newdevps.dto.place.response;

public interface PlaceDTOInterface {

    Long getId();
    Double getLatitude();
    Double getLongitude();
    String getCategory();
    String getPlaceName();
    String getAddress();
    String getCategoryName();
    Double getStarRating();
    String getDetailUrl();
    String getImageUrl();
    String getBookmarkStatus();
}
