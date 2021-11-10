package hu.temalabor.GetFit.repository;

import hu.temalabor.GetFit.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Integer> {
}
