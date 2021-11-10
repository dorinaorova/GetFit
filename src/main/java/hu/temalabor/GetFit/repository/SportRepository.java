package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Sport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SportRepository extends MongoRepository<Sport, Integer> {
}
