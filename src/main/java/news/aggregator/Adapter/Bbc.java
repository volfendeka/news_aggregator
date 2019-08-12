package news.aggregator.Adapter;

import java.util.ArrayList;

public class Bbc extends FeedAdapter {

    private final ArrayList feedMap;

    public Bbc() {
        super("bbc");
        this.feedMap = new ArrayList();
    }

}
