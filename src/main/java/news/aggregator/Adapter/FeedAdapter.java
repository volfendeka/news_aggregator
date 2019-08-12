package news.aggregator.Adapter;

import java.lang.reflect.Constructor;

public class FeedAdapter {

    private String sourceName;

    private static String guid           = "guid";
    private static String title          = "title";
    private static String description    = "description";
    private static String link           = "link";
    private static String pubDate        = "pubDate";

    public FeedAdapter(String name)
    {
        this.sourceName = name;
    }


    public FeedAdapter make()
    {
        this.sourceName = String.format("%s%s", this.sourceName.substring(0, 1).toUpperCase(), this.sourceName.substring(1));

        try {
            Class<?> Adapter = Class.forName(this.sourceName);
            Constructor<?> cons = Adapter.getConstructor(String.class);
            Object adapterObject = cons.newInstance("MyAttributeValue");

            return (FeedAdapter) adapterObject;
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }

        return this;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        FeedAdapter.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        FeedAdapter.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        FeedAdapter.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        FeedAdapter.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        FeedAdapter.pubDate = pubDate;
    }
}
