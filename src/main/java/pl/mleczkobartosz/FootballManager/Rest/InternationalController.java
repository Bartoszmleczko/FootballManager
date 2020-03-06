package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.International;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.InternationalRepository;


import java.util.Optional;

@RestController
public class InternationalController {

   private final InternationalRepository internationalRepository;

    public InternationalController(InternationalRepository internationalRepository) {
        this.internationalRepository = internationalRepository;
    }

    @GetMapping("/internationals")
    public Page<International> findAll(Optional<String> name, Pageable pageable){
        return internationalRepository.findInternationalByName(name.orElse("_"),pageable);
    }

    @GetMapping("/internationals/{id}")
    public International findById(@PathVariable Long id){
        return internationalRepository.findById(id).orElseThrow(() ->new CustomNotFoundException(new International(),id));
    }

    @PostMapping("/internationals")
    public International saveInternational(@RequestBody International international){
        return internationalRepository.save(international);
    }

    @PutMapping("/internationals/{id}")
    public International updateInternational(@PathVariable Long id, @RequestBody International international){
        International dbInternational = internationalRepository.findById(id).orElseThrow(() ->new CustomNotFoundException(new International(),id));
        dbInternational.setInternationalName(international.getInternationalName());
        return internationalRepository.save(dbInternational);
    }

    @DeleteMapping("/international/{id}")
    public String deleteInternational(@PathVariable Long id){
        International international = internationalRepository.findById(id).orElseThrow(() ->new CustomNotFoundException(new International(),id));
        internationalRepository.delete(international);
        return "International deleted";
    }



}
