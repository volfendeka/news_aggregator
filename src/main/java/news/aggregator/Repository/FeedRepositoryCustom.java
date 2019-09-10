package news.aggregator.Repository;

import news.aggregator.Entity.Feed;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class FeedRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    /**
     * All feeds
     * @param limit
     * @return
     */
    public List<Feed> getFeeds(Integer limit) {
        String sql = "SELECT * FROM feed ORDER BY date_created DESC LIMIT " + limit;
        Query query = em.createNativeQuery(sql, Feed.class);
        return query.getResultList();
    }

    /**
     * Feeds by source
     * @return
     */
    public List<Object[]> getFeedsBySource() {
        String sql = "SELECT COUNT(f.id) amount, s.name FROM feed f " +
                     "LEFT JOIN source s ON s.id = f.source_id " +
                     "GROUP BY s.name ";
        Query query = em.createNativeQuery(sql);

        List<Object[]> result = query.getResultList();

        return result;
    }

    /**
     * Feeds by day
     * @return
     */
    public List<Object[]> getFeedsByDay() {
        String sql = "SELECT COUNT(f.id) amount, DATE(f.date_published) as pub_date FROM feed f " +
                     "GROUP BY pub_date " +
                     "ORDER BY pub_date ASC";
        Query query = em.createNativeQuery(sql);

        List<Object[]> result = query.getResultList();

        return result;
    }

    /**
     * Feeds by source and day
     * @return
     */
    public List<Object[]> getFeedsBySourceDay() {

        Date date = new Date();
        String todayDate= new SimpleDateFormat("yyyy-MM-dd").format(date);


        String sql = "SELECT COUNT(f.id) amount, s.name, DATE(f.date_published) as pub_date FROM feed f " +
                     "LEFT JOIN source s ON s.id = f.source_id " +
                     "WHERE f.date_published > '" + todayDate + " 00:00:00' " +
                     "GROUP BY pub_date, s.name ";
        Query query = em.createNativeQuery(sql);

        List<Object[]> result = query.getResultList();

        System.out.println(sql);

        return result;
    }

    public List<Object[]> getLastTenFeedsperSource()
    {
        String sql = "SELECT FROM source s " +
                "LEFT JOIN feed s ON s.id = f.source_id LIMIT 10";
        Query query = em.createNativeQuery(sql);

        List<Object[]> result = query.getResultList();

        return result;

    }

    /**
     * Feeds counters
     *
     * @return
     */
    public List<Object[]> getCounters() {

        String sql = "SELECT COUNT(*) as feeds_amount FROM feed ";
        Query query = em.createNativeQuery(sql);

        return query.getResultList();

    }
}