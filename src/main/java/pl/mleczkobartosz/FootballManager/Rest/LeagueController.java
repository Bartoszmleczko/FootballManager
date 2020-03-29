package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Model.Fixture;
import pl.mleczkobartosz.FootballManager.Model.FixtureGenerator;
import pl.mleczkobartosz.FootballManager.Model.FootballMatch;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.LeagueRepository;
import pl.mleczkobartosz.FootballManager.Service.LeagueService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class LeagueController {

private final LeagueService leagueService;

    public LeagueController(LeagueService leagueService) {
        this.leagueService = leagueService;
    }

    @GetMapping("/leagues")
    public Page<League> findAll(Optional<String> leagueName, Pageable pageable){
        return leagueService.findAll(leagueName,pageable);
    }

    @GetMapping("/leagues/{id}")
    public League findById(@PathVariable Long id){
        return leagueService.findById(id);
    }

    @PostMapping("/leagues")
    public League saveLeague(@RequestBody League league){
        return leagueService.save(league);
    }

    @PutMapping("/leagues/{id}")
    public League updateLeague(@PathVariable Long id, @RequestBody League league){
        return leagueService.update(id,league);
    }

    @DeleteMapping("/leagues/{id}")
    public String deleteLeague(@PathVariable Long id){
       return  leagueService.delete(id);
    }

    @GetMapping("leagues/{id}/clubs")
    public Set<Club> getLeaguesClubs(@PathVariable Long id){
        return leagueService.findClubs(id);
    }

    @GetMapping("leagues/{id}/fixtures")
    public List<Fixture> getFixturesList(@PathVariable Long id){
        FixtureGenerator generator = new FixtureGenerator(leagueService);
        return generator.generate(id);

    }





}
