package news.aggregator.Entity;

import antlr.StringUtils;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Feed {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String guid;

    private String title;

    @Column(columnDefinition = "text")
    private String description;

    private String link;

    private String mediaContent;

    private Date datePublished;

    private Date dateCreated;

    @ManyToOne
    @JoinColumn
    private FeedCategory category;

    @ManyToOne
    @JoinColumn
    private Source source;


    public Boolean isValid()
    {
        if(
                this.getGuid() == null ||
                        this.getTitle() == null ||
                        this.getDescription() == null ||
                        this.getFeedCategory() == null ||
                        this.getLink() == null ||
                        this.getDatePublished() == null //||
                        //this.getMediaContent() == null
        )
        {
            return false;
        }
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
        if(guid.length() > 254){
            this.guid = guid.substring(0, 254);
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMediaContent() {
        return mediaContent;
    }

    public void setMediaContent(String mediaContent) {
        this.mediaContent = mediaContent;
        if(mediaContent.length() > 254){
            this.mediaContent = mediaContent.substring(0, 254);
        }
    }

    public Date getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(Date datePublished) {
        this.datePublished = datePublished;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public FeedCategory getFeedCategory() {
        return category;
    }

    public void setFeedCategory(FeedCategory category) {
        this.category = category;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}