package news.aggregator.Repository;

import news.aggregator.Entity.FeedCategory;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface FeedCategoryRepository extends CrudRepository<FeedCategory, Integer> {

}