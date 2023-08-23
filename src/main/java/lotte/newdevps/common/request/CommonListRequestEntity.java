package lotte.newdevps.common.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class CommonListRequestEntity<T> {

    private List<T> requestList;

    public CommonListRequestEntity(List<T> requestList) {
        this.requestList = requestList;
    }
}
