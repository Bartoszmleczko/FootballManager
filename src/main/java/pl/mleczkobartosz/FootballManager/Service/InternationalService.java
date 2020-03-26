package pl.mleczkobartosz.FootballManager.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.FootballManager.Entity.International;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.InternationalRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class InternationalService {

    private final InternationalRepository internationalRepository;

    public InternationalService(InternationalRepository internationalRepository) {
        this.internationalRepository = internationalRepository;
    }

    @Transactional
    public Page<International> findAll(Optional<String> name, Pageable pageable){
        return internationalRepository.findInternationalByName(name.orElse("_"),pageable);
    }

    @Transactional
    public International findById(Long id){
        return internationalRepository.findById(id).orElseThrow(() ->new CustomNotFoundException(new International(),id));
    }

    @Transactional
    public International save( International international){
        return internationalRepository.save(international);
    }

    @Transactional
    public International update(Long id, International international){
        International dbInternational = internationalRepository.findById(id).orElseThrow(() ->new CustomNotFoundException(new International(),id));
        dbInternational.setInternationalName(international.getInternationalName());
        return internationalRepository.save(dbInternational);
    }

    @Transactional
    public String delete(Long id){
        International international = internationalRepository.findById(id).orElseThrow(() ->new CustomNotFoundException(new International(),id));
        internationalRepository.delete(international);
        return "International deleted";
    }



}
