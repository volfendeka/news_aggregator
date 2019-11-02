package news.aggregator.Controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import news.aggregator.Entity.Feed;
import news.aggregator.Entity.Source;
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

    @RequestMapping("/runner/start")
    public ResponseEntity<Object> startFeedRunner() {

        System.out.println("runner started");

        List<Source> response = feedRunner.init();

        System.out.println("runner finished");

        if(response.size() > 0){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @RequestMapping("/runner/stop")
    public ResponseEntity<Object> stopFeedRunner() {

        System.out.println("runner stop");

        List<Source> response = feedRunner.destroy();

        if(response.size() > 0){
            return ResponseEntity.ok().body(response);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(response);
    }

    @GetMapping("/{limit}")
    public @ResponseBody
    Iterable<Feed> getAllFeeds(@PathVariable int limit) {
        // This returns a JSON or XML with the users
        return feedRepositoryCustom.getFeeds(limit);
    }

    @GetMapping("/{limit}/{country_id}")
    public @ResponseBody
    Iterable<Feed> getFilteredFeeds(@PathVariable int limit, @PathVariable String country_id) {
        // This returns a JSON or XML with the users
        return feedRepositoryCustom.getFilteredFeeds(limit, country_id);
    }
}