package lotte.newdevps.ui;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RouteaApiController {

    @GetMapping("/daum")
    public String daum() {
        return "daum";
    }
}
