package news.aggregator.Adapter;

import java.util.ArrayList;
import java.util.Date;

public class New_york_times_technology extends FeedAdapter {

    private final ArrayList feedMap;

    public New_york_times_technology() {
        super("new_york_times_technology");
        this.feedMap = new ArrayList();
    }

    public static Date convertPublishedDate()
    {

        return new Date();

    }

}
