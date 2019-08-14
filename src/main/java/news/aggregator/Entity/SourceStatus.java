package news.aggregator.Entity;

import javax.persistence.*;
import java.util.Set;

@Entity // This tells Hibernate to make a table out of this class
public class SourceStatus {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    private String name;

    @OneToMany(mappedBy = "sourceStatus", cascade = CascadeType.ALL)
    private Set<Source> source;

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

    public Set<Source> getSource() {
        return source;
    }

    public void setSource(Set<Source> source) {
        this.source = source;
    }

}