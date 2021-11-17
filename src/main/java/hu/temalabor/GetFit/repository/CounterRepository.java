package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.Counter;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CounterRepository extends MongoRepository<Counter, String> {
}
