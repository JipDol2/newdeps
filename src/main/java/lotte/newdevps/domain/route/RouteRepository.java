package lotte.newdevps.domain.route;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RouteRepository extends JpaRepository<Route, Long> {

    @Query(value = "SELECT loginId, routeId, title, startdate, enddate, image FROM route_info WHERE loginId =:userId", nativeQuery = true)
    List<Route> findByRouteList(@Param("userId") String userId);

    @Modifying @Transactional
    @Query(value = "DELETE FROM route_info WHERE loginId =:userId AND routeId =:routeId", nativeQuery = true)
    void deleteRoute(@Param("userId") String userId, @Param("routeId") String routeId);

    @Modifying @Transactional
    @Query(value = "INSERT INTO route_info (routeId, loginId, title, startdate, enddate, image,insert_ymd ) " +
            "VALUE(:routeId, :userId, :title, :startdate, :enddate ,:image,:insert_ymd)", nativeQuery = true)
    void saveRoute(@Param("routeId") String routeId, @Param("userId") String userId,@Param("title") String title,
                   @Param("startdate") String startdate, @Param("enddate") String enddate,@Param("image") String image,
                   @Param("insert_ymd") String insert_ymd);

    @Modifying @Transactional
    @Query(value = "INSERT INTO route_address (routeId, address_order, address, latitude, longitude, insert_ymd ) " +
            "VALUE(:routeId, :address_order, :address, :latitude, :longitude ,:insert_ymd)", nativeQuery = true)
    void saveRouteAddress(@Param("routeId") String routeId,@Param("address_order") int address_order,
                          @Param("address") String address,@Param("latitude") String latitude,
                          @Param("longitude") String longitude, @Param("insert_ymd")String insert_ymd);


}


