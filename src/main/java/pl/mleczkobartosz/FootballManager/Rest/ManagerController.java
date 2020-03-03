package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Manager;
import pl.mleczkobartosz.FootballManager.Exception.ManagerNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.ManagerRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ManagerController {

    private final ManagerRepository managerRepository;

    public ManagerController(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @GetMapping("/managers")
    public Page<Manager> findAll(Optional<String> firstName, Optional<String> lastName, Optional<Integer> birthYear, Pageable pageable){
        if(!birthYear.isPresent())
            return managerRepository.findManagerByFirstNameAndLastName(firstName.orElse("_"),lastName.orElse("_"),pageable);

        return managerRepository.findManagerByFirstNameAndLastNameAndBirthYear(firstName.orElse("_"),lastName.orElse("_"), birthYear.get(),pageable);
    }

    @GetMapping("/managers/{id}")
    public Manager findById(@PathVariable Long id){
        return managerRepository.findById(id).orElseThrow(() -> new ManagerNotFoundException(id));
    }

    @PostMapping("/managers")
    public Manager saveManager(@RequestBody Manager manager){
        return managerRepository.save(manager);
    }

    @PutMapping("/managers/{id}")
    public Manager updateManager(@PathVariable Long id, @RequestBody Manager manager){
        Manager dbManager = managerRepository.findById(id).orElseThrow(() -> new ManagerNotFoundException(id));

        dbManager.setFirstName(manager.getFirstName());
        dbManager.setLastName(manager.getLastName());
        dbManager.setBirthYear(manager.getBirthYear());
        dbManager.setClub(manager.getClub());
        return managerRepository.save(dbManager);
    }

    @DeleteMapping("/managers/{id}")
    public String deleteManager(@PathVariable Long id){
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new ManagerNotFoundException(id));
        managerRepository.delete(manager);
        return "Manager has been deleted";

    }

}
