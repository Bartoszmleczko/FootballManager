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

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class ClubController {

    private final ClubRepository clubRepository;
    private final ManagerRepository managerRepository;
    private final LeagueRepository leagueRepository;


    public ClubController(ClubRepository clubRepository, ManagerRepository managerRepository, LeagueRepository leagueRepository) {
        this.clubRepository = clubRepository;
        this.managerRepository = managerRepository;
        this.leagueRepository = leagueRepository;
    }

    @GetMapping("/clubs")
    public Page<Club> findAll(Optional<String> name, Optional<Integer> year, Pageable pageable){
        if(!year.isPresent())
            return clubRepository.findByClubName(name.orElse("_"),pageable);

        return clubRepository.findByClubNameAndFoundationYear(name.orElse("_"),year.get(),pageable);
    }

    @GetMapping("/clubs/{id}")
    public Club getClub(@PathVariable Long id){
        return clubRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Club(),id));
    }

    @PostMapping("/clubs")
    public Club saveClub(@Valid @RequestBody ClubModel clubModel){

        Club club =  new Club();
        League league = leagueRepository.findById(clubModel.getLeagueId()).orElseThrow(() -> new CustomNotFoundException(new League(),clubModel.getLeagueId()));
        Manager manager = managerRepository.findById(clubModel.getManagerId()).orElseThrow(() -> new CustomNotFoundException(new Manager(),clubModel.getManagerId()));

        club.setClubName(clubModel.getClubName());
        club.setFoundationYear(clubModel.getFoundationYear());
        club.setLeague(league);
        if(manager.getClub()==null){
            club.setMenager(manager);
        }else
        {
            throw new ManagerAlreadyHasAClubException(manager.getMenager_id());
        }
        club.setPlayers(null);

        return clubRepository.save(club);
    }

    @PutMapping("/clubs/{id}")
    public Club updateClub(@PathVariable Long id,@Valid @RequestBody ClubModel clubModel){
        Club dbClub = clubRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Club(),id));
        dbClub.setClubName(clubModel.getClubName());
        dbClub.setFoundationYear(clubModel.getFoundationYear());
        dbClub.setMenager( managerRepository.findById(clubModel.getManagerId()).orElseThrow(() -> new CustomNotFoundException(new Manager(),clubModel.getManagerId())));
        dbClub.setLeague( leagueRepository.findById( clubModel.getLeagueId()).orElseThrow(() -> new CustomNotFoundException(new League(), clubModel.getLeagueId())) );
        return clubRepository.save(dbClub);
    }

    @DeleteMapping("/clubs/{id}")
    public String deleteClub(@PathVariable Long id){
        Club dbClub = clubRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Club(),id));
        clubRepository.delete(dbClub);
        return "Club deleted";
    }


}
