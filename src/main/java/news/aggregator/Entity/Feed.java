package news.aggregator.Entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Feed {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
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

    @Transient
    private Set<FeedCategory> categories = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    private Source source;


    public Boolean isValid()
    {
        if(
                this.getGuid() == null ||
                this.getTitle() == null ||
                this.getDescription() == null ||
                this.getLink() == null ||
                this.getDatePublished() == null
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

    public void addCategory(FeedCategory category)
    {
        this.categories.add(category);
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

    public Set<FeedCategory> getCategories() {
        return categories;
    }

    public void setCategories(Set<FeedCategory> categories) {
        this.categories = categories;
    }

    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }
}