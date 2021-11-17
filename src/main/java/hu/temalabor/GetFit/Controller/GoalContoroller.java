package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.Counter;
import hu.temalabor.GetFit.model.Goal;
import hu.temalabor.GetFit.repository.CounterRepository;
import hu.temalabor.GetFit.repository.GoalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/GoalController")
public class GoalContoroller {
    private GoalRepository goalRepository;
    private CounterRepository counterRepository;

    public GoalContoroller(GoalRepository goalRepository, CounterRepository counterRepository) {
        this.goalRepository = goalRepository;
        this.counterRepository = counterRepository;
    }

    //Osszes Goal lekerese
    @GetMapping("/")
    @ResponseBody
    List<Goal> GetGoals(){
        return goalRepository.findAll();
    }

    //Egy Goal kerese id alapjan
    @GetMapping("/{id}")
    Optional<Goal> GetGoalById(@PathVariable(value = "id") int id){
        return goalRepository.findById(id);
    }

    @GetMapping("/userId={id}")
    List<Goal> GetGoalByUserId(@PathVariable(value = "id") int id){
        return goalRepository.findByUserId(id);
    }

    @GetMapping("/date={date}/{id}")
    List<Goal> GetGoalForAWeek(@PathVariable(value="date") Date date, @PathVariable(value="id") int id){
        List<Goal> goals =goalRepository.findByUserId(id);
        List<Goal> goalsForAWeek = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int days= calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE,-days); //first day of week

        for(Goal a : goals){
            Calendar cal = calendar.getInstance();
            cal.setTime(a.getDateStart());
            cal.add(Calendar.DATE, -7); //8?

            if(id == a.getUserId() && calendar.after(cal)){
                cal.add(Calendar.DATE, 7); //9?
                if(calendar.before(cal))  goalsForAWeek.add(a);
            }
        }
        return goalsForAWeek;
    }

    @DeleteMapping("/{id}")
    void DeleteGoalById(@PathVariable(value="id") int id){
        goalRepository.deleteById(id);
    }

    @PostMapping
    void NewGoal(@RequestBody Goal newGoal){

        Counter cnt= null;
        Optional<Counter> c = counterRepository.findById("Goal");
        if(c.isPresent()){
            cnt = c.get();
            cnt.increaseCounter();
            counterRepository.save(cnt);
        }
        newGoal.set_id(cnt.getCounter());
        goalRepository.save(newGoal);
    }

    @PutMapping("/{id}")
    void updateGoal(@PathVariable(value="id") int id, @RequestBody Goal uGoal){
        Optional<Goal> goalData = goalRepository.findById(id);
        if(goalData.isPresent()){
            Goal goal = goalData.get();
            goal=uGoal;
            goal.set_id(id);
            goalRepository.save(goal);
        }
    }


}
