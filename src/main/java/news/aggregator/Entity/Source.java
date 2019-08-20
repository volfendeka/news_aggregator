package news.aggregator.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Source {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String baseUri;

    private String rssUri;

    @ManyToOne(fetch = FetchType.LAZY)
    private SourceStatus sourceStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    private SourceType sourceType;

    @ManyToOne(fetch = FetchType.LAZY)
    private Country country;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Feed> feeds;

    @ManyToMany(mappedBy = "sources", fetch = FetchType.LAZY)
    private Set<SourceConfiguration> sourceConfigurations = new HashSet<>();

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

    public SourceType getSourceType() {
        return sourceType;
    }

    public void setSourceType(SourceType sourceType) {
        this.sourceType = sourceType;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public SourceStatus getSourceStatus() {
        return sourceStatus;
    }

    public void setSourceStatus(SourceStatus sourceStatus) {
        this.sourceStatus = sourceStatus;
    }

    public Set<Feed> getFeeds() {
        return feeds;
    }

    public void setFeeds(Set<Feed> feeds) {
        this.feeds = feeds;
    }

    public Set<SourceConfiguration> getSourceConfigurations() {
        return sourceConfigurations;
    }
    public void setSourceConfigurations(Set<SourceConfiguration> sourceConfigurations) {
        this.sourceConfigurations = sourceConfigurations;
    }

}