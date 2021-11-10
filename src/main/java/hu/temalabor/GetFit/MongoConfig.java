package hu.temalabor.GetFit;

import hu.temalabor.GetFit.model.Activity;
import hu.temalabor.GetFit.model.Goal;
import hu.temalabor.GetFit.model.Sport;
import hu.temalabor.GetFit.model.User;
import hu.temalabor.GetFit.repository.ActivityRepository;
import hu.temalabor.GetFit.repository.GoalRepository;
import hu.temalabor.GetFit.repository.SportRepository;
import hu.temalabor.GetFit.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.util.Date;

@EnableMongoRepositories
@Configuration
public class MongoConfig {
    @Bean
    CommandLineRunner commandLineRunnerSport(SportRepository sportRepository){
        return String ->{
            sportRepository.save(new Sport(1,"Futas", 0.207));
            sportRepository.save(new Sport(2,"Kocogas", 0.169));
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerGoal(GoalRepository goalRepository){
        return String ->{
            goalRepository.save(new Goal(1, 4, 1, new Date(2021,10,22)));
            goalRepository.save(new Goal(2, 3, 2, new Date(2021,10,25)));
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerUser(UserRepository userRepository){
        return String ->{
            userRepository.save(new User(1, "kisbela", "Kis Bela", "kisbela@kisbela.hu", "pw1"));
            userRepository.save(new User(2, "nagyadi", "Nagy Adam", "nagyadi@nagyadi.hu", "pw2" ));
        };
    }

    @Bean
    CommandLineRunner commandLineRunnerActivity(ActivityRepository activityRepository){
        return String ->{
            activityRepository.save(new Activity(1, 1, 1, 60, new Date(2021,10,22)));
            activityRepository.save(new Activity(2, 2, 1, 30, new Date(2021,10,23)));
        };
    }
}
