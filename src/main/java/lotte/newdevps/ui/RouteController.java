package lotte.newdevps.ui;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import lotte.newdevps.application.RouteService;
import lotte.newdevps.common.response.CommonListResponseEntity;
import lotte.newdevps.common.response.CommonResponseEntity;
import lotte.newdevps.common.response.ResponseType;
import lotte.newdevps.dto.route.request.RouteSaveDTO;
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
    public CommonListResponseEntity<RouteDTO> findRouteList(String userId){
        List<RouteDTO> routelist = routeService.findRouteList(userId);
        return CommonListResponseEntity.toListResponseEntity(ResponseType.I001,routelist,routelist.size());
    }
    //노선 삭제
    @NoAuth
    @DeleteMapping(value = "/delete")
    public CommonResponseEntity<Void> deleteRoute(String userId, String routeId){
        routeService.deleteRoute(userId, routeId);
        return CommonResponseEntity.toResponseEntity(ResponseType.I002,null,1);
    }

    //노선 등록
    @NoAuth
    @PostMapping(value = "/save")
    public CommonResponseEntity<RouteDTO> saveRoute(RouteSaveDTO routeDto){
        routeService.saveRoute(routeDto);
        return CommonResponseEntity.toResponseEntity(ResponseType.I002,null,1);
    }


}