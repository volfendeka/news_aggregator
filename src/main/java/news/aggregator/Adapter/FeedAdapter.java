package news.aggregator.Adapter;

import news.aggregator.Entity.SourceConfiguration;

import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class FeedAdapter implements FeedAdapterInterface{

    private String guid           = "guid";
    private String title          = "title";
    private String description    = "description";
    private String link           = "link";
    private String pubDate        = "pubDate";
    private String item           = "item";
    private String mediaContent   = "media:content";
    private String url            = "url";
    private String category       = "category";

    public FeedAdapter()
    {

    }

    public void setConfigurations(Set<SourceConfiguration> sourceConfiguration)
    {
        for (SourceConfiguration config: sourceConfiguration) {

            String confName = config.getName();
            String ConfigName = String.format("%s%s", confName.substring(0, 1).toUpperCase(), confName.substring(1));
            try{
                Method setterMethod = this.getClass().getMethod("set" + ConfigName, String.class);
                setterMethod.invoke(this, config.getValue());
            }catch(Exception exception){
                System.out.println(config.getName() + ": " + config.getValue() + " Config doesn't exist " + exception.getMessage());
            }
        }
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

    public void setPubDatePattern(String pubDatePattern) {
        List<String> parsePatterns =  this.getParsePatterns();

        parsePatterns.add(pubDate);
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
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
