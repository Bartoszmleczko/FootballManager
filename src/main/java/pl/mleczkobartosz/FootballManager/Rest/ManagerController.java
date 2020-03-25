package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Manager;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.ManagerRepository;
import pl.mleczkobartosz.FootballManager.Service.ManagerService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ManagerController {


    private final ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping("/managers")
    public Page<Manager> findAll(Optional<String> firstName, Optional<String> lastName, Optional<Integer> birthYear, Pageable pageable){
        return managerService.findAll(firstName,lastName,birthYear,pageable);
    }

    @GetMapping("/managers/{id}")
    public Manager findById(@PathVariable Long id){
        return managerService.findById(id);
    }

    @PostMapping("/managers")
    public Manager saveManager(@Valid @RequestBody Manager manager){
        return managerService.save(manager);
    }

    @PutMapping("/managers/{id}")
    public Manager updateManager(@PathVariable Long id,
                                 @Valid @RequestBody Manager manager){
        return managerService.updateManager(id,manager);
    }

    @DeleteMapping("/managers/{id}")
    public String deleteManager(@PathVariable Long id){
        return managerService.delete(id);
    }

}
