package lotte.newdevps.dto.route.response;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lotte.newdevps.domain.route.RouteAddress;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class RouteAddressDTO {
    private int address_order;
    private String address;
    private double latitude;
    private double longitude;

    @Builder
    public RouteAddressDTO(int address_order, String address, double latitude, double longitude) {
        this.address_order = address_order;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public static List<RouteAddressDTO> toRouteDtoList(List<RouteAddress> routeAddress){
        return routeAddress.stream()
                .map(addressInfo -> RouteAddressDTO.builder()
                        .address_order(addressInfo.address_order)
                        .address(addressInfo.address)
                        .latitude(addressInfo.latitude)
                        .longitude(addressInfo.longitude)
                        .build())
                .collect(Collectors.toList());
    }

}
