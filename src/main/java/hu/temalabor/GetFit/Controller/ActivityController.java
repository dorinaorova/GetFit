package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.Activity;
import hu.temalabor.GetFit.model.Counter;
import hu.temalabor.GetFit.model.Goal;
import hu.temalabor.GetFit.repository.ActivityRepository;
import hu.temalabor.GetFit.repository.CounterRepository;
import hu.temalabor.GetFit.repository.GoalRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ActivityController")
public class ActivityController {

    GoalRepository goalRepository;
    ActivityRepository activityRepository;
    CounterRepository counterRepository;
    public ActivityController(ActivityRepository activityRepository, GoalRepository goalRepository, CounterRepository counterRepository){
        this.activityRepository=activityRepository;
        this.goalRepository=goalRepository;
        this.counterRepository=counterRepository;
    }


    //Osszes Activity lekerese
    @GetMapping("/")
    public List<Activity> GetActivities(){
        return activityRepository.findAll();
    }

    //Egy Activity kerese id alapjan
    @GetMapping("/{id}")
    public Optional<Activity> GetActivityById(@PathVariable(value = "id") int id){
        return activityRepository.findById(id);
    }

    @GetMapping("/userId={id}")
    public List<Activity> GetActivityByUserId(@PathVariable(value = "id") int id){
        return activityRepository.findByUserId(id);
    }

    @GetMapping("/sportId={id}")
    public List<Activity> GetActivityBySportId(@PathVariable(value = "id") int id){
        return activityRepository.findBySportId(id);
    }

    @GetMapping("/week={date}/{id}")
    public List<Activity> GetActivitiesForAWeek(@PathVariable(value = "date") Date date, @PathVariable(value = "id") int id){
        List<Activity> activities = activityRepository.findByUserId(id);
        List<Activity> activitiesForAWeek = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        int days= calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE,-days); //first day of week

        for(Activity a : activities){
            Calendar cal = calendar.getInstance();
            cal.setTime(a.getDate());
            cal.add(Calendar.DATE, -7); //8?

            if(id == a.getUserId() && calendar.after(cal)){
                cal.add(Calendar.DATE, 7); //9?
                if(calendar.before(cal))  activitiesForAWeek.add(a);
            }
        }
        return activitiesForAWeek;
    }

    @DeleteMapping("/{id}")
    void DeleteActivityById(@PathVariable(value="id") int id){
        activityRepository.deleteById(id);
    }

    @PostMapping
    void NewActivity(@RequestBody Activity newActivity){
        Counter cnt= null;
        Optional<Counter> c = counterRepository.findById("Activity");
        if(c.isPresent()){
            cnt = c.get();
            cnt.increaseCounter();
            counterRepository.save(cnt);
        }
        newActivity.set_id(cnt.getCounter());
        activityRepository.save(newActivity);

        activityRepository.save(newActivity);

        List<Goal> goals = goalRepository.findByUserId(newActivity.getUserId());

        Goal goal=null;
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(newActivity.getDate());
        int days= calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE,-days); //first day of week

        for(Goal a : goals){
            Calendar cal = calendar.getInstance();
            cal.setTime(a.getDateStart());
            cal.add(Calendar.DATE, -7); //8?

            if(newActivity.getUserId() == a.getUserId() && calendar.after(cal)){
                cal.add(Calendar.DATE, 7); //9?
                if(calendar.before(cal)) {
                    goal=a;
                    break;
                }
            }
        }

        goal.setAmount(1);

        Optional<Goal> goalData = goalRepository.findById(goal.get_id());
        if(goalData.isPresent()){
            Goal uGoal = goalData.get();
            uGoal=goal;
            goalRepository.save(uGoal);
        }
    }

    @PutMapping("/{id}")
    void updateActivity(@PathVariable(value="id") int id, @RequestBody Activity uActivity){
        Optional<Activity> activityData = activityRepository.findById(id);
        if(activityData.isPresent()){
            Activity activity = activityData.get();
            activity=uActivity;
            activity.set_id(id);
            activityRepository.save(activity);
        }
    }

}

