package news.aggregator.Adapter;

import java.lang.reflect.Constructor;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FeedAdapter {

    private String sourceName;

    private static String guid           = "guid";
    private static String title          = "title";
    private static String description    = "description";
    private static String link           = "link";
    private static String pubDate        = "pubDate";
    private static String item           = "item";

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


    public List<String> getParsePatterns()
    {
        List <String> patterns = new LinkedList<String>(Arrays.asList(
                "E MMM dd HH:mm:ss Z yyyy",
                "d-MMM-yyyy h:mm:ss a",
                "EE, MMMM d, yyyy h:mm:ss a z",
                "E, d MM yyyy hh:mm:ss Z",
                "E, d MM yyyy hh:mm:ss z",
                "EEE, d MMM yyyy HH:mm:ss Z",
                "E, d M y hh:mm:ss Z",
                "E, d MMM yyyy hh:mm:ss Z"
        ));
        return patterns;
    }

    public Date convertDate(String dateString, List<String> parsePatterns)
    {
        Integer lastIndex = parsePatterns.size() - 1;
        String parsePattern = parsePatterns.get(lastIndex);
        try{
            parsePatterns.remove(parsePattern);
            DateFormat formatter = new SimpleDateFormat(parsePattern);

            return formatter.parse(dateString);
        } catch (ParseException exception){
            System.out.println(exception.toString());
            return this.convertDate(dateString,parsePatterns);
        }catch(Exception exception){
            System.out.println(exception.toString());
            return new Date();
        }
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


    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        FeedAdapter.item = item;
    }
}
