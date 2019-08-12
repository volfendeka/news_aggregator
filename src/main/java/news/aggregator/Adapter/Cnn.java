package news.aggregator.Adapter;

import java.util.ArrayList;
import java.util.Date;

public class Cnn extends FeedAdapter {

    private final ArrayList feedMap;

    public Cnn() {
        super("cnn");
        this.feedMap = new ArrayList();
    }

    public static Date convertPublishedDate()
    {

        return new Date();

    }

}
