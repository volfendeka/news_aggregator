package news.aggregator.Adapter;

import java.util.ArrayList;
import java.util.Date;

public class New_york_times extends FeedAdapter {

    private final ArrayList feedMap;

    public New_york_times() {
        super("new_york_time");
        this.feedMap = new ArrayList();
    }

    public static Date convertPublishedDate()
    {

        return new Date();

    }

}
