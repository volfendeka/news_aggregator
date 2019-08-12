package news.aggregator.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class Source {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String baseUri;

    private String rssUri;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL)
    private Set<Feed> feed;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseUri() {
        return baseUri;
    }

    public void setBaseUri(String baseUri) {
        this.baseUri = baseUri;
    }

    public String getRssUri() {
        return rssUri;
    }

    public void setRssUri(String rssUri) {
        this.rssUri = rssUri;
    }

    public Set<Feed> getFeed() {
        return feed;
    }

    public void setFeed(Set<Feed> feed) {
        this.feed = feed;
    }
}