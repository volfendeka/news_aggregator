package news.aggregator.Repository;

import news.aggregator.Entity.Source;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class SourceRepositoryCustom{
    @PersistenceContext
    private EntityManager em;

    public List<Source> getActiveSources() {
        String sql = "SELECT * FROM source WHERE source_status_id = 1";
        Query query = em.createNativeQuery(sql, Source.class);
        return query.getResultList();
    }
}