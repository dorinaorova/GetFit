package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ActivityRepository extends MongoRepository<Activity, Integer> {
    Optional<Activity> findByUserId(int userId);
}
