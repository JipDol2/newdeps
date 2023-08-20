package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import lotte.newdevps.application.RouteService;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.route.request.RouteSaveDTO;
import lotte.newdevps.dto.route.response.RouteAddressDTO;
import lotte.newdevps.dto.route.response.RouteDTO;

import lotte.newdevps.ui.auth.NoAuth;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/route")
public class RouteController {

    private final RouteService routeService;
    //노선 리스트 전체 조회
    @NoAuth
    @GetMapping(value = "/list")
    public CommonListResponseEntity<RouteDTO> findRouteList(String loginId){
        List<RouteDTO> routelist = routeService.findRouteList(loginId);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.I001,routelist,routelist.size());
    }
    //노선 주소 정보 조회
    @NoAuth
    @GetMapping(value = "/address")
    public CommonListResponseEntity<RouteAddressDTO> findRouteaddress(String routeId){
        List<RouteAddressDTO> addresslist = routeService.findRouteaddress(routeId);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.I001,addresslist,addresslist.size());
    }
    //노선 삭제
    @NoAuth
    @DeleteMapping(value = "/delete")
    public CommonResponseEntity<Void> deleteRoute(String loginId, String routeId){
        routeService.deleteRoute(loginId, routeId);
        return CommonResponseEntity.toResponseEntity(ResponseType.I002,null,1);
    }

    //노선 등록
    @NoAuth
    @PostMapping(value = "/save")
    public CommonResponseEntity<RouteDTO> insertRoute(RouteSaveDTO routeSaveDTO){
        routeService.insertRoute(routeSaveDTO);
        return CommonResponseEntity.toResponseEntity(ResponseType.I002,null,1);
    }

    //노선 수정
    @NoAuth
    @PutMapping(value = "/save")
    public CommonResponseEntity<RouteDTO> updateRoute(RouteSaveDTO routeSaveDTO){
        routeService.updateRoute(routeSaveDTO);
        return CommonResponseEntity.toResponseEntity(ResponseType.I002,null,1);
    }



}