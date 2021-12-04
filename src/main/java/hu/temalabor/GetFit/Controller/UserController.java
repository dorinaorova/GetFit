package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.Activity;
import hu.temalabor.GetFit.model.Counter;
import hu.temalabor.GetFit.model.Goal;
import hu.temalabor.GetFit.model.User;
import hu.temalabor.GetFit.repository.ActivityRepository;
import hu.temalabor.GetFit.repository.CounterRepository;
import hu.temalabor.GetFit.repository.GoalRepository;
import hu.temalabor.GetFit.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/UserController")
public class UserController {
    private UserRepository userRepository;
    private CounterRepository counterRepository;
    private ActivityRepository activityRepository;
    private GoalRepository goalRepository;

    public UserController(UserRepository userRepository, CounterRepository counterRepository, ActivityRepository activityRepository, GoalRepository goalRepository) {
        this.userRepository = userRepository;
        this.counterRepository=counterRepository;
        this.activityRepository = activityRepository;
        this.goalRepository=goalRepository;
    }


    //Osszes User lekerese
    @GetMapping("/")
    List<User> GetUsers(){
        return userRepository.findAll();
    }

    //Egy User kerese id alapjan
    @GetMapping("/{id}")
    Optional<User> GetUserById(@PathVariable(value = "id") int id){
        return userRepository.findById(id);
    }

    @GetMapping("/name={name}")
    List<User> GetUserByName(@PathVariable(value="name") String name){
        List<User> users= userRepository.findAll();
        List<User> usersByName = new ArrayList<>();
        for(User u: users){
          if(u.getUsername().contains(name)){
              usersByName.add(u);
          }
        }
        return usersByName;
    }

    @DeleteMapping("/{id}")
    void DeleteUserById(@PathVariable(value="id") int id){
        userRepository.deleteById(id);
        List<Goal> goals= goalRepository.findByUserId(id);
        for (Goal g: goals){
            goalRepository.deleteById(g.get_id());
        }
        List<Activity> activities= activityRepository.findByUserId(id);
        for (Activity a: activities){
            activityRepository.deleteById(a.get_id());
        }
    }

    @PostMapping
    void NewUser(@RequestBody User newUser){
        Counter cnt= null;
        Optional<Counter> c = counterRepository.findById("User");
        if(c.isPresent()){
            cnt = c.get();
            cnt.increaseCounter();
            counterRepository.save(cnt);
        }
        newUser.set_id(cnt.getCounter());
        newUser.setPoints(0);
        newUser.setLevel();
        userRepository.save(newUser);

    }

    @PutMapping("/{id}")
    void updateUser(@PathVariable(value="id") int id, @RequestBody User uUser){
        Optional<User> userData = userRepository.findById(id);
        if(userData.isPresent()){
            User user = userData.get();
            int points = user.getPoints();
            user=uUser;
            user.set_id(id);
            user.setPoints(points);
            user.setLevel();
            userRepository.save(user);
        }
    }


}
