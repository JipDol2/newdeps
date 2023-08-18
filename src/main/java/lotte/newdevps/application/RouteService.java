package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import lotte.newdevps.domain.route.Route;
import lotte.newdevps.domain.route.RouteRepository;

import lotte.newdevps.dto.route.request.RouteSaveDTO;
import lotte.newdevps.dto.route.response.RouteDTO;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class RouteService {
    private final RouteRepository routeRepository;

    public List<RouteDTO> findRouteList(String userId) {
        List<Route> routes = routeRepository.findByRouteList(userId);
        return RouteDTO.toRouteDtoList(routes);
    }

    public void deleteRoute(String userId, String routeId) {
        routeRepository.deleteRoute(userId,routeId);
    }

    public void saveRoute(RouteSaveDTO routeDto) {
        LocalDateTime now = LocalDateTime.now();
        String formatNow1 = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String insert_ymd = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String routeId = routeDto.getLoginId() + formatNow1;
        List<String> addressList = routeDto.getAddressList();
        List<String> latitude = routeDto.getLatitude();
        List<String> longitude = routeDto.getLongitude();


        routeRepository.saveRoute(routeId,
                                  routeDto.getLoginId(),
                                  routeDto.getTitle(),
                                  routeDto.getStartdate(),
                                  routeDto.getEnddate(),
                                  routeDto.getImage(),
                                  insert_ymd
                                  );



        for (int i=0; i<addressList.size(); i++){
            routeRepository.saveRouteAddress(routeId,
                                            i+1,
                                            addressList.get(i),
                                            latitude.get(i),
                                            longitude.get(i),
                                            insert_ymd);

        }

    }



}
