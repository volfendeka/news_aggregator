package news.aggregator.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

}