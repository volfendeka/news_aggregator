package news.aggregator.Controller;

import news.aggregator.Repository.FeedRepositoryCustom;
import news.aggregator.Repository.SourceRepositoryCustom;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/stats")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class StatsController {

    @Autowired
    private FeedRepositoryCustom feedRepositoryCustom;
    @Autowired
    private SourceRepositoryCustom sourceRepositoryCustom;

    @RequestMapping("/")
    public String index() {
        return "Greetings from Spring Boot!";
    }

    @GetMapping("/feeds_by_source")
    public @ResponseBody
    Iterable<Object[]> getFeedsBySource() {
        // This returns a JSON or XML with the users
        return feedRepositoryCustom.getFeedsBySource();
    }

    @GetMapping("/feeds_by_day")
    public @ResponseBody
    Iterable<Object[]> getFeedsByDay() {
        // This returns a JSON or XML with the users
        return feedRepositoryCustom.getFeedsByDay();
    }

    @GetMapping("/feeds_by_source_day")
    public @ResponseBody
    Iterable<Object[]> getFeedsBySourceDay() {
        // This returns a JSON or XML with the users
        return feedRepositoryCustom.getFeedsBySourceDay();
    }

    @GetMapping("/sources")
    public @ResponseBody Iterable<Object[]> getSourceStats() {
        // This returns a JSON or XML with the users
        return sourceRepositoryCustom.getStatsBySource();
    }

    @RequestMapping("/general_counters")
    public ResponseEntity<Object> getGeneralCounters() {

        HashMap<String, List> map = new HashMap<>();

        List<Object[]> feedsCounters = feedRepositoryCustom.getCounters();
        List<Object[]> sourceCounters = sourceRepositoryCustom.getCounters();

        map.put("feeds_amount", feedsCounters);
        map.put("active_sources", sourceCounters);

        return ResponseEntity.ok().body(map);

    }

}