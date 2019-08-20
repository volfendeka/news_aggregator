package news.aggregator.Repository;

import news.aggregator.Entity.Feed;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class FeedRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    public List<Feed> getAllFeeds() {
        String sql = "SELECT * FROM feed LIMIT 20";
        Query query = em.createNativeQuery(sql, Feed.class);
        return query.getResultList();
    }
}