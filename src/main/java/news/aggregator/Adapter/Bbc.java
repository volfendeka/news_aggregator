package news.aggregator.Adapter;

import java.util.ArrayList;
import java.util.List;

public class Bbc extends FeedAdapter {

    private String pubDatePattern = "E, d MMM yyyy hh:mm:ss Z";

    private final ArrayList feedMap;

    public Bbc() {
        super("bbc");
        this.feedMap = new ArrayList();
    }

    @Override
    public List<String> getParsePatterns() {
        List<String> parsePatterns =  super.getParsePatterns();

        parsePatterns.add(this.pubDatePattern);

        return parsePatterns;
    }
}
