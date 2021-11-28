package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.Counter;
import hu.temalabor.GetFit.model.Goal;
import hu.temalabor.GetFit.repository.CounterRepository;
import hu.temalabor.GetFit.repository.GoalRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
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
    List<Goal> GetGoalForAWeek(@PathVariable(value="date") long date, @PathVariable(value="id") int id){
        //find goals by user id: --> user's goals
        List<Goal> goals =goalRepository.findByUserId(id);
        return  getGoalForAWeekFun(date, goals);
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

    public List<Goal> getGoalForAWeekFun(long date, List<Goal> goals){
        List<Goal> goalsForAWeek=new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime( new Timestamp(date));
        int days= calendar.get(Calendar.DAY_OF_WEEK);
        if (days==1) days+=7;
        days-=calendar.getFirstDayOfWeek();
        calendar.add(Calendar.DATE,-days); //first day of week

        for(Goal a : goals){
            Calendar cal = Calendar.getInstance();
            Timestamp ts = new Timestamp(a.getDateStart()); //the day when the goal started
            cal.setTime(ts);

            if(calendar.before(cal)){
                calendar.add(Calendar.DATE, 7); //9?
                if(calendar.after(cal))  {
                    goalsForAWeek.add(a);

                }
                calendar.add(Calendar.DATE, -7);
            }
        }
        return goalsForAWeek;
    }


}
