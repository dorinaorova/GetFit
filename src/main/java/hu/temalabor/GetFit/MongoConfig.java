package hu.temalabor.GetFit;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

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
//    @Bean
//    CommandLineRunner commandLineRunnerGoal(GoalRepository goalRepository){
//        return String ->{
//            goalRepository.save(new Goal(1, 4, 1, Timestamp.from(Instant.now()).getTime()));
//            goalRepository.save(new Goal(2, 3, 2, Timestamp.from(Instant.now()).getTime()));
//        };
//    }
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
//            activityRepository.save(new Activity(1, 1, 1, 60, Timestamp.from(Instant.now()).getTime()));
//            activityRepository.save(new Activity(2, 2, 1, 30, Timestamp.from(Instant.now()).getTime()));
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
