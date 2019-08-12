package news.aggregator.Repository;

import news.aggregator.Entity.Source;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface SourceRepository extends CrudRepository<Source, Integer> {
/*
    @Query("SELECT s FROM source s WHERE s.name = :name")
    Source findByName(@Param("name") String name);
*/
}