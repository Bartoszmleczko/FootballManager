package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.LeagueRepository;

import java.util.Optional;

@RestController
public class LeagueController {

private final LeagueRepository leagueRepository;
private final ClubRepository clubRepository;

    public LeagueController(LeagueRepository leagueRepository, ClubRepository clubRepository) {
        this.leagueRepository = leagueRepository;
        this.clubRepository = clubRepository;
    }

    @GetMapping("/leagues")
    public Page<League> findAll(Optional<String> leagueName, Pageable pageable){
        return leagueRepository.findByLeagueName(leagueName.orElse("_"),pageable);
    }

    @GetMapping("/leagues/{id}")
    public League findById(@PathVariable Long id){
        return leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new League(),id));
    }

    @PostMapping("/leagues")
    public League saveLeague(@RequestBody League league){
        return leagueRepository.save(league);
    }

    @PutMapping("/leagues/{id}")
    public League updateLeague(@PathVariable Long id, @RequestBody League league){
        League dbLeague = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new League(),id));
        dbLeague.setLeagueName(league.getLeagueName());
        dbLeague.setClubs(league.getClubs());
        return leagueRepository.save(dbLeague);
    }

    @DeleteMapping("/leagues/{id}")
    public String deleteLeague(@PathVariable Long id){
        League league = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new League(),id));
        return "League deleted";
    }

    @PutMapping("/leagues/{leagueId}/club/{clubId}")
    public League addClubToLeague(@PathVariable(value = "leagueId",required = true) Long leagueId, @PathVariable(value = "clubId",required = true) Long clubId){
        League league = leagueRepository.findById(leagueId).orElseThrow(() -> new CustomNotFoundException(new League(),leagueId));
        Club club = clubRepository.findById(clubId).orElseThrow(() -> new CustomNotFoundException(new Club(),clubId));

        league.getClubs().add(club);
        return leagueRepository.save(league);

    }




}
