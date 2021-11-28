package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.*;
import hu.temalabor.GetFit.repository.*;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping("/ActivityController")
public class ActivityController {

    GoalRepository goalRepository;
    ActivityRepository activityRepository;
    CounterRepository counterRepository;
    UserRepository userRepository;
    SportRepository sportRepository;
    public ActivityController(ActivityRepository activityRepository, GoalRepository goalRepository, CounterRepository counterRepository, SportRepository sportRepository, UserRepository userRepository){
        this.activityRepository=activityRepository;
        this.goalRepository=goalRepository;
        this.counterRepository=counterRepository;
        this.sportRepository = sportRepository;
        this.userRepository=userRepository;
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

    @GetMapping("/id={id}/sportId={sportid}")
    public List<Activity> GetActivityBySportId(@PathVariable(value = "sportid") int sportid, @PathVariable(value = "id") int id){
        List<Activity> activitiesBySportID= activityRepository.findBySportId(sportid);
        List<Activity> activities = new ArrayList<>();
        for(Activity a: activitiesBySportID){
            if(a.getUserId()==id){
                activities.add(a);
            }
        }
        return activities;
    }

    @GetMapping("/week={date}/{id}")
    public List<Activity> GetActivitiesForAWeek(@PathVariable(value = "date") long date, @PathVariable(value = "id") int id){
        List<Activity> activities = activityRepository.findByUserId(id);
        List<Activity> activitiesForAWeek = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime( new Timestamp(date));
        int days= calendar.get(Calendar.DAY_OF_WEEK);
        calendar.add(Calendar.DATE,-days+calendar.getFirstDayOfWeek()); //first day of week

        for(Activity a : activities){
            Calendar cal = Calendar.getInstance();
            Timestamp ts = new Timestamp(a.getDate());
            cal.setTime(new Date(ts.getTime()));

            if(calendar.before(cal)){
                calendar.add(Calendar.DATE, 7); //9?
                if(calendar.after(cal))  {
                    activitiesForAWeek.add(a);
                    calendar.add(Calendar.DATE, -7); //9?

                }
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



        List<Goal> goals = goalRepository.findByUserId(newActivity.getUserId());
        //find the goal for the week
        GoalContoroller ac = new GoalContoroller(goalRepository, counterRepository);
        Goal goal= ac.getGoalForAWeekFun(newActivity.getDate(), goals).get(0);
        //TODO: not founded --> make new goal for the week

        User user=null;
        Optional<User> userData = userRepository.findById(newActivity.getUserId());
        if (userData.isPresent()) {
            user = userData.get();
        }

        Sport sp = null;
        newActivity.set_id(cnt.getCounter());
        Optional<Sport> sportData = sportRepository.findById(newActivity.getSportId());
        System.out.println(sportData);
        if(sportData.isPresent()){
            sp= sportData.get();
            System.out.println(sp.get_id());
        }
        newActivity.setKcal(sp.getKcal(), user.getWeight());
        activityRepository.save(newActivity);

        //found --> is this succesfull?
        if(goal!=null) {

            goal.setAmount(1);

            Optional<Goal> goalData = goalRepository.findById(goal.get_id());
            if (goalData.isPresent()) {
                Goal uGoal = goalData.get();
                uGoal = goal;
                uGoal.SetStatus();
                goalRepository.save(uGoal);
            }


            if (goal.getStatus() == 1) {
                //user gets points
                    user.setPoints(goal.getAmount());
                    user.setLevel();
                    userRepository.save(user);
                }
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

