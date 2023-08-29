package lotte.newdevps.dto.place.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.place.PlaceCategory;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class PlaceSaveDTO {

    private Double latitude;
    private Double longitude;
    private PlaceCategory category;
    private String placeName;
    private String address;
    private String categoryName;
    private Double starRating;

    public static List<Place> toEntityList(List<PlaceSaveDTO> placeDTO){
        return placeDTO.stream()
                .map(placeDto->Place.builder()
                        .latitude(placeDto.getLatitude())
                        .longitude(placeDto.getLongitude())
                        .placeName(placeDto.getPlaceName())
                        .category(placeDto.getCategory())
                        .address(placeDto.getAddress())
                        .categoryName(placeDto.getCategoryName())
                        .starRating(placeDto.getStarRating())
                        .build())
                .collect(Collectors.toList());
    }
}
