package news.aggregator.Controller;

import news.aggregator.Entity.Feed;
import news.aggregator.Entity.User;
import news.aggregator.Repository.FeedRepository;
import news.aggregator.Repository.FeedRepositoryCustom;
import news.aggregator.Service.FeedRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/feed")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class FeedController {

    @Autowired
    public FeedRunner feedRunner;
    @Autowired
    private FeedRepositoryCustom feedRepositoryCustom;
    @Autowired
    private FeedRepository feedRepository;


    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @RequestMapping("/gather")
    public String launchFeedRunner() {

        System.out.println("runner started");

        String response = feedRunner.init();

        System.out.println("ru  nner finished");
        return response;
    }

    @RequestMapping(
            value = "/get/all"
            //method = RequestMethod.GET,
            //produces = { MimeTypeUtils.APPLICATION_JSON_VALUE },
            //headers = "Accept=application/json"
    )
    public @ResponseBody
    Iterable<Feed> getAllFeeds() {
        // This returns a JSON or XML with the users
        return feedRepository.findAll();
    }


}