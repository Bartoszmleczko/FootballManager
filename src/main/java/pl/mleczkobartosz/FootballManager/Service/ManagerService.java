package pl.mleczkobartosz.FootballManager.Service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.FootballManager.Entity.Manager;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.ManagerRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ManagerService {

    private final ManagerRepository managerRepository;

    public ManagerService(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Transactional
    public Page<Manager> findAll(Optional<String> firstName, Optional<String> lastName, Optional<Integer> birthYear, Pageable pageable){
        if(!birthYear.isPresent())
            return managerRepository.findManagerByFirstNameAndLastName(firstName.orElse("_"),lastName.orElse("_"),pageable);

        return managerRepository.findManagerByFirstNameAndLastNameAndBirthYear(firstName.orElse("_"),lastName.orElse("_"), birthYear.get(),pageable);
    }

    @Transactional
    public Manager findById(Long id){
        return managerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(Manager.class.getSimpleName(),id));
    }

    @Transactional
    public Manager save(Manager manager){
        return managerRepository.save(manager);
    }

    @Transactional
    public Manager updateManager (Long id, Manager manager){
        Manager dbManager = managerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(Manager.class.getSimpleName(),id));

        dbManager.setFirstName(manager.getFirstName());
        dbManager.setLastName(manager.getLastName());
        dbManager.setBirthYear(manager.getBirthYear());
        return managerRepository.save(dbManager);
    }

    @Transactional
    public String delete(Long id){
        Manager manager = managerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(Manager.class.getSimpleName(),id));
        managerRepository.delete(manager);
        return "Manager has been deleted";
    }

}
