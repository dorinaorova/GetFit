package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Activity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;


public interface ActivityRepository extends MongoRepository<Activity, Integer> {
    List<Activity> findByUserId(int userId);
    List<Activity> findBySportId(int sportId);
}
