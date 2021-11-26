package hu.temalabor.GetFit;

import hu.temalabor.GetFit.model.Goal;
import hu.temalabor.GetFit.repository.GoalRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.Timestamp;
import java.time.Instant;

@EnableMongoRepositories
@Configuration
public class MongoConfig {
//    @Bean
//    CommandLineRunner commandLineRunnerSport(SportRepository sportRepository){
//        return String ->{
//            sportRepository.save(new Sport(1,"Futas", 0.207));
//            sportRepository.save(new Sport(2,"Kocogas", 0.169));
//        };
//    }
//
    @Bean
    CommandLineRunner commandLineRunnerGoal(GoalRepository goalRepository){
        return String ->{
            goalRepository.save(new Goal(1, 4, 1, Long.parseLong(Timestamp.from(Instant.now()).toString())));
            goalRepository.save(new Goal(2, 3, 2, Long.parseLong(Timestamp.from(Instant.now()).toString())));
        };
    }
//
//    @Bean
//    CommandLineRunner commandLineRunnerUser(UserRepository userRepository){
//        return String ->{
//            userRepository.save(new User(1, "kisbela", "Kis Bela", "kisbela@kisbela.hu", "pw1"));
//            userRepository.save(new User(2, "nagyadi", "Nagy Adam", "nagyadi@nagyadi.hu", "pw2" ));
//        };
//    }
//
//    @Bean
//    CommandLineRunner commandLineRunnerActivity(ActivityRepository activityRepository){
//        return String ->{
//            activityRepository.save(new Activity(1, 1, 1, 60, new Date(2021,10,22)));
//            activityRepository.save(new Activity(2, 2, 1, 30, new Date(2021,10,23)));
//        };
//    }
//
//    @Bean
//    CommandLineRunner commandLineRunnerCounter(CounterRepository counterRepository){
//        return String ->{
//            counterRepository.save(new Counter("Activity", 2));
//            counterRepository.save(new Counter("Sport", 2));
//            counterRepository.save(new Counter("User", 2));
//            counterRepository.save(new Counter("Goal", 2));
//        };
//    }
}
