package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface GoalRepository extends MongoRepository<Goal, Integer> {
}
