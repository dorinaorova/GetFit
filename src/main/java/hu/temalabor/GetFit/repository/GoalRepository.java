package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Goal;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface GoalRepository extends MongoRepository<Goal, Integer> {
    List<Goal> findByUserId(int id);

}
