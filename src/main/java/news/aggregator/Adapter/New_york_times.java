package news.aggregator.Adapter;

import java.util.ArrayList;
import java.util.List;

public class New_york_times extends FeedAdapter {

    private String pubDatePattern = "E, d MMM yyyy hh:mm:ss Z";
    private final ArrayList feedMap;

    public New_york_times() {
        super("new_york_times");
        this.feedMap = new ArrayList();
    }

    @Override
    public List<String> getParsePatterns() {
        List<String> parsePatterns =  super.getParsePatterns();

        parsePatterns.add(this.pubDatePattern);

        return parsePatterns;
    }

}
