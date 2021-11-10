package hu.temalabor.GetFit.Controller;


import hu.temalabor.GetFit.model.User;
import hu.temalabor.GetFit.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/UserController")
public class UserController {
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    }

    @PostMapping
    void NewUser(@RequestBody User newUser){
        userRepository.save(newUser);
    }

    @PutMapping
    void updateUser(@PathVariable(value="id") int id, @RequestBody User uUser){
        Optional<User> userData = userRepository.findById(id);
        if(userData.isPresent()){
            User user = userData.get();
            user=uUser;
            userRepository.save(user);
        }
    }


}
