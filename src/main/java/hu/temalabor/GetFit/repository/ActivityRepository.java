package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, Integer> {
}
