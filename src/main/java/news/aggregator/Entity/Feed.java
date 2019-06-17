package news.aggregator.Entity;

import javax.persistence.*;
import java.util.Date;

@Entity // This tells Hibernate to make a table out of this class
public class Feed {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String description;

    private Date datePublished;

    private Date dateCreated;

    @ManyToOne
    @JoinColumn
    private FeedCategory categoryId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return categoryId;
    }

    public void setFeedCategory(FeedCategory categoryId) {
        this.categoryId = categoryId;
    }
}