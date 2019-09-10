package news.aggregator.Repository;

import news.aggregator.Entity.Source;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SourceRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    /**
     * Active sources for FeedRunner
     * @return
     */
    public List<Source> getActiveSources() {
        String sql = "SELECT * FROM source WHERE source_status_id = 1";
        Query query = em.createNativeQuery(sql, Source.class);
        return query.getResultList();
    }

    //todo: grab monthly weekly dayly stats from summary table
    public Iterable<Object[]> getStatsBySource()
    {
        String sql = "SELECT * FROM source WHERE source_status_id = 1";
        Query query = em.createNativeQuery(sql, Source.class);
        return query.getResultList();
    }

    /**
     * Sources counters
     *
     * @return
     */
    public List<Object[]> getCounters() {

        String sql = "SELECT COUNT(*) sources_amount, ss.`name` FROM source s " +
                     "LEFT JOIN source_status ss ON ss.id = s.source_status_id " +
                     "WHERE ss.`name` = 'active'" +
                     "GROUP BY ss.`name`";
        Query query = em.createNativeQuery(sql);

        List<Object[]> result = query.getResultList();

        return query.getResultList();

    }

}