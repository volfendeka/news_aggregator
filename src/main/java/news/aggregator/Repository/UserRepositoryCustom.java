package news.aggregator.Repository;

import news.aggregator.Entity.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserRepositoryCustom {
    @PersistenceContext
    private EntityManager em;

    public User findByUsername(String username) {
        String sql = "SELECT * FROM user WHERE username =  '" + username + "'";
        Query query = em.createNativeQuery(sql, User.class);
        Iterable<User> users = query.getResultList();

        User user = ((List<User>) users).get(0);
        return user;
    }
}