package lotte.newdevps.domain.route;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface RouteAddressReposiroty  extends JpaRepository<RouteAddress,  Long> {

        @Query(value = "SELECT address_order, address, latitude, longitude FROM route_address WHERE routeId =:routeId ORDER BY address_order", nativeQuery = true)
        List<RouteAddress> findByadderss(@Param("routeId") String routeId);

        @Modifying
        @Transactional
        @Query(value = "DELETE FROM route_address WHERE routeId =:routeId", nativeQuery = true)
        void deleteaddress(@Param("routeId") String routeId);

        @Modifying @Transactional
        @Query(value = "INSERT INTO route_address (routeId, address_order, address, latitude, longitude, insert_ymd ) " +
                "VALUE(:routeId, :address_order, :address, :latitude, :longitude ,:insert_ymd)", nativeQuery = true)
        void insertRouteAddress(@Param("routeId") String routeId,@Param("address_order") int address_order,
                              @Param("address") String address,@Param("latitude") String latitude,
                              @Param("longitude") String longitude, @Param("insert_ymd")String insert_ymd);


}
