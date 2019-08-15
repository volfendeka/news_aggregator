package news.aggregator.Adapter;

import java.util.ArrayList;
import java.util.List;

public class Radiosvoboda extends FeedAdapter {

    private final String  mediaContentCustom = "enclosure";
    private String pubDatePattern = "E, d MMM yyyy hh:mm:ss Z";

    private final ArrayList feedMap;

    public Radiosvoboda() {
        super();
        this.feedMap = new ArrayList();
        this.setMediaContent(mediaContentCustom);
    }

    @Override
    public List<String> getParsePatterns() {
        List<String> parsePatterns =  super.getParsePatterns();

        parsePatterns.add(this.pubDatePattern);

        return parsePatterns;
    }
}
