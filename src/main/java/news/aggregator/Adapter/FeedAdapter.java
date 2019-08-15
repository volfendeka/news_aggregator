package news.aggregator.Adapter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FeedAdapter {

    private String sourceName;

    private String guid           = "guid";
    private String title          = "title";
    private String description    = "description";
    private String link           = "link";
    private String pubDate        = "pubDate";
    private String item           = "item";
    private String mediaContent   = "media:content";

    public FeedAdapter()
    {

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
        this.guid = guid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }
}
