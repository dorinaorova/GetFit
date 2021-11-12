package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface GoalRepository extends MongoRepository<Goal, Integer> {
    Optional<Goal> findByUserId(int userId);
}
