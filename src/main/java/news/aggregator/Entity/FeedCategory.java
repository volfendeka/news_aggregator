package news.aggregator.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class FeedCategory {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
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

    public Set<Feed> getFeed() {
        return feed;
    }

    public void setFeed(Set<Feed> feed) {
        this.feed = feed;
    }
}