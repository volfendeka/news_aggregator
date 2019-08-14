package news.aggregator.Adapter;

import java.util.ArrayList;
import java.util.Date;

public class New_york_times_news extends FeedAdapter {

    private final ArrayList feedMap;

    public New_york_times_news() {
        super("new_york_times_news");
        this.feedMap = new ArrayList();
    }

    public static Date convertPublishedDate()
    {

        return new Date();

    }

}
