package lotte.newdevps.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import lotte.newdevps.domain.route.Route;
import lotte.newdevps.domain.route.RouteAddress;
import lotte.newdevps.domain.route.RouteAddressReposiroty;
import lotte.newdevps.domain.route.RouteRepository;

import lotte.newdevps.dto.route.request.RouteSaveDTO;
import lotte.newdevps.dto.route.response.RouteAddressDTO;
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
    private final RouteAddressReposiroty routeAddressReposiroty;

    public List<RouteDTO> findRouteList(String loginId) {
        List<Route> routes = routeRepository.findByRouteList(loginId);
        return RouteDTO.toRouteDtoList(routes);
    }

    public List<RouteAddressDTO> findRouteaddress(String routeId){
        List<RouteAddress> addressInfo = routeAddressReposiroty.findByadderss(routeId);
        return RouteAddressDTO.toRouteDtoList(addressInfo);
    }

    public void deleteRoute(String loginId, String routeId) {
        routeRepository.deleteRoute(loginId,routeId);
        routeAddressReposiroty.deleteaddress(routeId);
    }

    public void insertRoute(RouteSaveDTO routeSaveDTO) {
        LocalDateTime now = LocalDateTime.now();
        String formatNow1 = now.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        String insert_ymd = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String routeId = routeSaveDTO.getLoginId() + formatNow1;
        List<String> addressList = routeSaveDTO.getAddressList();
        List<String> latitude = routeSaveDTO.getLatitude();
        List<String> longitude = routeSaveDTO.getLongitude();


        routeRepository.insertRoute(routeId,
                routeSaveDTO.getLoginId(),
                routeSaveDTO.getTitle(),
                routeSaveDTO.getStartdate(),
                routeSaveDTO.getEnddate(),
                routeSaveDTO.getImage(),
                insert_ymd);



        for (int i=0; i<addressList.size(); i++){
            routeAddressReposiroty.insertRouteAddress(routeId,
                                            i+1,
                                            addressList.get(i),
                                            latitude.get(i),
                                            longitude.get(i),
                                            insert_ymd);

        }

    }

    public  void updateRoute(RouteSaveDTO routeSaveDTO){
        LocalDateTime now = LocalDateTime.now();
        String mod_ymd = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        List<String> addressList = routeSaveDTO.getAddressList();
        List<String> latitude = routeSaveDTO.getLatitude();
        List<String> longitude = routeSaveDTO.getLongitude();

        routeRepository.updateRoute(routeSaveDTO.getRouteId(),
                routeSaveDTO.getLoginId(),
                routeSaveDTO.getTitle(),
                routeSaveDTO.getStartdate(),
                routeSaveDTO.getEnddate(),
                routeSaveDTO.getImage(),
                mod_ymd);

        routeAddressReposiroty.deleteaddress(routeSaveDTO.getRouteId());

        for (int i=0; i<addressList.size(); i++){
            routeAddressReposiroty.insertRouteAddress(routeSaveDTO.getRouteId(),
                    i+1,
                    addressList.get(i),
                    latitude.get(i),
                    longitude.get(i),
                    mod_ymd);

        }

    }

}
