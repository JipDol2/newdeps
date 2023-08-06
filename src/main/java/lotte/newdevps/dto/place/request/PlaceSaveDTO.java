package lotte.newdevps.dto.place.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.place.PlaceCategory;
import lotte.newdevps.dto.place.response.PlaceDTO;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class PlaceSaveDTO {

    private Double latitude;
    private Double longitude;
    private PlaceCategory category;
    private String placeName;

    public static List<Place> toEntityList(List<PlaceSaveDTO> placeDTO){
        return placeDTO.stream()
                .map(placeDto->Place.builder()
                        .latitude(placeDto.getLatitude())
                        .longitude(placeDto.getLongitude())
                        .placeName(placeDto.getPlaceName())
                        .category(placeDto.getCategory())
                        .build())
                .collect(Collectors.toList());
    }
}
