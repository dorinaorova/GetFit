package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.Activity;
import hu.temalabor.GetFit.repository.ActivityRepository;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/ActivityController")
public class ActivityController {


    ActivityRepository activityRepository;
    public ActivityController(ActivityRepository activityRepository){
        this.activityRepository=activityRepository;
    }

    //Osszes Activity lekerese
    @GetMapping("/")
    List<Activity> GetActivities(){
        return activityRepository.findAll();
    }

    //Egy Activity kerese id alapjan
    @GetMapping("/{id}")
    public Optional<Activity> GetActivityById(@PathVariable(value = "id") int id){
        return activityRepository.findById(id);
    }

    @GetMapping("/week={date}/{id}")
    public List<Activity> GetActivitiesForAWeek(@PathVariable(value = "date") Date date, @PathVariable(value = "id") int id){
        List<Activity> activities = activityRepository.findAll();
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
        activityRepository.save(newActivity);
    }

    @PutMapping
    void updateActivity(@PathVariable(value="id") int id, @RequestBody Activity uActivity){
        Optional<Activity> activityData = activityRepository.findById(id);
        if(activityData.isPresent()){
            Activity activity = activityData.get();
            activity=uActivity;
            activityRepository.save(activity);
        }
    }
}

