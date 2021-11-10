package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.Sport;
import hu.temalabor.GetFit.repository.SportRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/SportController")
public class SportController {

    private SportRepository sportRepository;

    public SportController(SportRepository sportRepository) {
        this.sportRepository = sportRepository;
    }

    //Osszes Sport lekerese
    @GetMapping("/")
    List<Sport> GetSports(){
        return sportRepository.findAll();
    }

    //Egy Sport kerese id alapjan
    @GetMapping("/{id}")
    Optional<Sport> GetSportById(@PathVariable(value = "id") int id){
        return sportRepository.findById(id);
    }

    @DeleteMapping("/{id}")
    void DeleteSportById(@PathVariable(value="id") int id){
        sportRepository.deleteById(id);
    }

    @PostMapping
    void NewSport(@RequestBody Sport newSport){
        sportRepository.save(newSport);
    }

    @PutMapping
    void updateSport(@PathVariable(value="id") int id, @RequestBody Sport uSport){
        Optional<Sport> sportData = sportRepository.findById(id);
        if(sportData.isPresent()){
            Sport sport = sportData.get();
            sport=uSport;
            sportRepository.save(sport);
        }
    }

}
