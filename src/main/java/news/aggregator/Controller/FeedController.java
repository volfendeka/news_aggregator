package news.aggregator.Controller;

import news.aggregator.Service.FeedRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeedController {

    @Autowired
    public FeedRunner feedRunner;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/feed/gather")
    public String launchFeedRunner() {

        System.out.println("runner started");

        String response = feedRunner.init();

        System.out.println("runner finished");
        return response;
    }

}