package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.International;
import pl.mleczkobartosz.FootballManager.Exception.InternationalNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.InternationalRepository;

import java.util.List;

@RestController
public class InternationalController {

   private final InternationalRepository internationalRepository;

    public InternationalController(InternationalRepository internationalRepository) {
        this.internationalRepository = internationalRepository;
    }

    @GetMapping("/internationals")
    public List<International> findAll(){
        return internationalRepository.findAll();
    }

    @GetMapping("/internationals/{id}")
    public International findById(@PathVariable Long id){
        return internationalRepository.findById(id).orElseThrow(() ->new  InternationalNotFoundException(id));
    }

    @PostMapping("/internationals")
    public International saveInternational(@RequestBody International international){
        return internationalRepository.save(international);
    }

    @PutMapping("/internationals/{id}")
    public International updateInternational(@PathVariable Long id, @RequestBody International international){
        International dbInternational = internationalRepository.findById(id).orElseThrow(() -> new InternationalNotFoundException(id));
        dbInternational.setInternationalName(international.getInternationalName());
        dbInternational.setPlayers(international.getPlayers());
        return internationalRepository.save(dbInternational);
    }

    @DeleteMapping("/international/{id}")
    public String deleteInternational(@PathVariable Long id){
        International international = internationalRepository.findById(id).orElseThrow(() -> new InternationalNotFoundException(id));
        internationalRepository.delete(international);
        return "International deleted";
    }



}
