package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;
import pl.mleczkobartosz.FootballManager.Entity.Manager;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Exception.ManagerAlreadyHasAClubException;
import pl.mleczkobartosz.FootballManager.Model.ClubModel;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.LeagueRepository;
import pl.mleczkobartosz.FootballManager.Repository.ManagerRepository;
import pl.mleczkobartosz.FootballManager.Service.ClubService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ClubController {

    private final ClubService clubService;

    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }

    @GetMapping("/clubs")
    public Page<Club> findAll(Optional<String> name, Optional<Integer> year, Pageable pageable){
        return clubService.findAll(name,year,pageable);
    }

    @GetMapping("/clubs/{id}")
    public Club getClub(@PathVariable Long id){
        return clubService.findById(id);
    }

    @PostMapping("/clubs")
    public Club saveClub(@Valid @RequestBody ClubModel clubModel){

        return clubService.save(clubModel);

    }

    @PutMapping("/clubs/{id}")
    public Club updateClub(@PathVariable Long id,@Valid @RequestBody ClubModel clubModel){
        return clubService.update(id,clubModel);
    }

    @DeleteMapping("/clubs/{id}")
    public String deleteClub(@PathVariable Long id){
        return clubService.delete(id);
    }


}
