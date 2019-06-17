package news.aggregator.Repository;

import news.aggregator.Entity.SourceType;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SourceTypeRepository extends CrudRepository<SourceType, Integer> {

}