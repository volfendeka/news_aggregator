package news.aggregator.Entity;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Date;
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

    private Date createdDate;

    private Date updatedDate;

    private Date lastRunDate;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SourceStatus sourceStatus;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private SourceType sourceType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Country country;

    @OneToMany(mappedBy = "source", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Feed> feeds;

    @OneToMany(mappedBy = "source", fetch = FetchType.EAGER)
    private Set<SourceConfiguration> sourceConfigurations;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getLastRunDate() {
        return lastRunDate;
    }

    public void setLastRunDate(Date lastRunDate) {
        this.lastRunDate = lastRunDate;
    }
}