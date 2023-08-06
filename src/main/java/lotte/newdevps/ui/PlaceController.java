package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.PlaceService;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.domain.place.Place;
import lotte.newdevps.dto.place.request.PlaceSaveDTO;
import lotte.newdevps.dto.place.response.PlaceDTO;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/place")
public class PlaceController {

    private final PlaceService placeService;

    /**
     * 추천 장소 단건 조회(L001)
     */
    @GetMapping("/{placeId}")
    public CommonResponseEntity<PlaceDTO> findByPlace(@PathVariable Long placeId){
        return CommonResponseEntity.toResponseEntity(ResponseType.L001,placeService.findByPlace(placeId),1);
    }

    /**
     * 추천 장소 목록 전체 조회(L002)
     */
    @GetMapping("/all")
    public CommonListResponseEntity<PlaceDTO> findByPlaceAll(){
        List<PlaceDTO> placeAll = placeService.findByPlaceAll();
        return CommonListResponseEntity.toListResponseEntity(ResponseType.L001,placeAll,placeAll.size());
    }

    /**
     * 추천 장소 저장(L003)
     */
    @PostMapping
    public CommonListResponseEntity<PlaceDTO> savePlace(@RequestBody List<PlaceSaveDTO> placeDTO){
        return CommonListResponseEntity.toListResponseEntity(null,placeService.save(placeDTO),0);
    }
}
