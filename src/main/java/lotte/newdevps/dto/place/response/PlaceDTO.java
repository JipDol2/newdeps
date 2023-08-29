package lotte.newdevps.dto.place.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lotte.newdevps.domain.place.Place;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
public class PlaceDTO {

    private Long id;
    private Double latitude;
    private Double longitude;
    private String category;
    private String placeName;
    private String address;
    private String categoryName;
    private Double starRating;

    @Builder
    public PlaceDTO(Long id, Double latitude, Double longitude, String category, String placeName,String address,String categoryName,Double starRating) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.placeName = placeName;
        this.address = address;
        this.categoryName = categoryName;
        this.starRating = starRating;
    }

    public static PlaceDTO toPlaceDto(Place place){
        return PlaceDTO.builder()
                .id(place.getId())
                .latitude(place.getLatitude())
                .longitude(place.getLongitude())
                .category(place.getCategory().name())
                .placeName(place.getPlaceName())
                .address(place.getAddress())
                .categoryName(place.getCategoryName())
                .starRating(place.getStarRating())
                .build();
    }
    public static List<PlaceDTO> toPlaceDtoList(List<Place> places){
        return places.stream()
                .map(place -> PlaceDTO.builder()
                        .id(place.getId())
                        .latitude(place.getLatitude())
                        .longitude(place.getLongitude())
                        .category(place.getCategory().name())
                        .placeName(place.getPlaceName())
                        .address(place.getAddress())
                        .categoryName(place.getCategoryName())
                        .starRating(place.getStarRating())
                        .build())
                .collect(Collectors.toList());
    }
}
