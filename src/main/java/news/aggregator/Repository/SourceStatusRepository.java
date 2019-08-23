package news.aggregator.Repository;

import news.aggregator.Entity.SourceStatus;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SourceStatusRepository extends CrudRepository<SourceStatus, Integer> {

}