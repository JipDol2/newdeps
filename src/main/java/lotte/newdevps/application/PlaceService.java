package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.domain.bookmark.Bookmark;
import lotte.newdevps.domain.bookmark.BookmarkRepository;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.domain.place.PlaceRepository;
import lotte.newdevps.dto.place.request.PlaceSaveDTO;
import lotte.newdevps.dto.place.response.PlaceDTO;
import lotte.newdevps.dto.place.response.PlaceDTOInterface;
import lotte.newdevps.exception.place.PlaceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class PlaceService {

    private final PlaceRepository placeRepository;

    public PlaceDTO findByPlace(Long id){
        Place place = placeRepository.findById(id)
                .orElseThrow(() -> new PlaceNotFoundException());
        return PlaceDTO.toPlaceDto(place);
    }

    public List<PlaceDTOInterface> findByPlaceAll(Long loginId){
        List<PlaceDTOInterface> placeDTOInterfaces = placeRepository.findByAllPlaceDTO(loginId);
        return placeDTOInterfaces;
    }

    public List<PlaceDTO> save(List<PlaceSaveDTO> placeDTO){
        return placeRepository.saveAll(PlaceSaveDTO.toEntityList(placeDTO)).stream()
                .map(place->PlaceDTO.toPlaceDto(place))
                .collect(Collectors.toList());
    }
}
