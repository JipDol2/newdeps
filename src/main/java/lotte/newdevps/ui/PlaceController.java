package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lotte.newdevps.application.PlaceService;
import lotte.newdevps.application.UserService;
import lotte.newdevps.common.request.CommonListRequestEntity;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.place.request.PlaceSaveDTO;
import lotte.newdevps.dto.place.response.PlaceDTO;
import lotte.newdevps.dto.place.response.PlaceDTOInterface;
import lotte.newdevps.ui.auth.Authentication;
import lotte.newdevps.ui.auth.LoginSession;
import lotte.newdevps.ui.auth.NoAuth;
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
    @NoAuth
    @GetMapping("/{placeId}")
    public CommonResponseEntity<PlaceDTO> findByPlace(@PathVariable Long placeId){
        return CommonResponseEntity.toResponseEntity(ResponseType.L001,placeService.findByPlace(placeId),1);
    }

    /**
     * 추천 장소 목록 전체 조회(L002)
     */
    @NoAuth
    @GetMapping("/all")
    public CommonListResponseEntity<PlaceDTO> findByPlaceAll(@Authentication LoginSession session){
        List<PlaceDTOInterface> placeAll = placeService.findByPlaceAll(session.getId());
        return CommonListResponseEntity.toListResponseEntity(ResponseType.L002,placeAll,placeAll.size());
    }

    /**
     * 추천 장소 저장(L003)
     */
    @NoAuth
    @PostMapping
    public CommonListResponseEntity<PlaceDTO> savePlace(@RequestBody CommonListRequestEntity<PlaceSaveDTO> placeDTO){
        placeDTO.getRequestList().forEach(placeDto-> log.info("placeDto : {}",placeDto.toString()));
        return CommonListResponseEntity.toListResponseEntity(ResponseType.L003,placeService.save(placeDTO.getRequestList()),0);
    }
}
