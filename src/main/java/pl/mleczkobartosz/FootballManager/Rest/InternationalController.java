package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.International;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.InternationalRepository;
import pl.mleczkobartosz.FootballManager.Service.InternationalService;


import java.util.Optional;

@RestController
public class InternationalController {

   private final InternationalService internationalService;

    public InternationalController(InternationalService internationalService) {
        this.internationalService = internationalService;
    }

    @GetMapping("/internationals")
    public Page<International> findAll(Optional<String> name, Pageable pageable){
        return internationalService.findAll(name,pageable);
    }

    @GetMapping("/internationals/{id}")
    public International findById(@PathVariable Long id){
        return internationalService.findById(id);
    }

    @PostMapping("/internationals")
    public International saveInternational(@RequestBody International international){
        return internationalService.save(international);
    }

    @PutMapping("/internationals/{id}")
    public International updateInternational(@PathVariable Long id, @RequestBody International international){
        return internationalService.update(id,international);
    }

    @DeleteMapping("/international/{id}")
    public String deleteInternational(@PathVariable Long id){
        return internationalService.delete(id);
    }



}
