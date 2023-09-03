package lotte.newdevps.domain.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface RouteRepository extends JpaRepository<Route,  Long> {

    @Query(value = "SELECT loginId, routeId, title, startdate, enddate, image FROM route_info WHERE loginId =:loginId", nativeQuery = true)
    List<Route> findByRouteList(@Param("loginId") String loginId);

    @Modifying @Transactional
    @Query(value = "DELETE FROM route_info WHERE loginId =:loginId AND routeId =:routeId", nativeQuery = true)
    void deleteRoute(@Param("loginId") String loginId, @Param("routeId") String routeId);

    @Modifying @Transactional
    @Query(value = "INSERT INTO route_info (routeId, loginId, title, startdate, enddate, image,insert_ymd ) " +
            "VALUE(:routeId, :userId, :title, :startdate, :enddate ,:image,:insert_ymd)", nativeQuery = true)
    void insertRoute(@Param("routeId") String routeId, @Param("userId") String userId,@Param("title") String title,
                   @Param("startdate") String startdate, @Param("enddate") String enddate,@Param("image") String image,
                   @Param("insert_ymd") String insert_ymd);

    @Modifying @Transactional
    @Query(value =  "UPDATE route_info SET title =:title, startdate =:startdate , enddate =:enddate ," +
            "image =:image, mod_ymd =:mod_ymd WHERE loginId =:userId AND routeId =:routeId", nativeQuery = true)
    void updateRoute(@Param("routeId") String routeId, @Param("userId") String userId,@Param("title") String title,
                     @Param("startdate") String startdate, @Param("enddate") String enddate,@Param("image") String image,
                     @Param("mod_ymd") String mod_ymd);
}


