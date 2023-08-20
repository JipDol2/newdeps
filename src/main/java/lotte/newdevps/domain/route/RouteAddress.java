package lotte.newdevps.domain.route;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name="ROUTE_ADDRESS")
public class RouteAddress {

    @Id
    public int address_order;
    public String address;
    public double latitude;
    public double longitude;

}
