package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.Goal;
import hu.temalabor.GetFit.repository.GoalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/GoalController")
public class GoalContoroller {
    private GoalRepository goalRepository;

    public GoalContoroller(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    //Osszes Goal lekerese
    @GetMapping("/")
    @ResponseBody
    List<Goal> GetGoals(){
        return goalRepository.findAll();
    }

    //Egy Sport kerese id alapjan
    @GetMapping("/{id}")
    @ResponseBody
    Optional<Goal> GetGoalById(@PathVariable(value = "id") int id){
        return goalRepository.findById(id);
    }


    @DeleteMapping("/{id}")
    void DeleteGoalById(@PathVariable(value="id") int id){
        goalRepository.deleteById(id);
    }

    @PostMapping
    void NewGoal(@RequestBody Goal newGoal){
        goalRepository.save(newGoal);
    }

    @PutMapping
    void updateGoal(@PathVariable(value="id") int id, @RequestBody Goal uGoal){
        Optional<Goal> goalData = goalRepository.findById(id);
        if(goalData.isPresent()){
            Goal goal = goalData.get();
            goal=uGoal;
            goalRepository.save(goal);
        }
    }


}
