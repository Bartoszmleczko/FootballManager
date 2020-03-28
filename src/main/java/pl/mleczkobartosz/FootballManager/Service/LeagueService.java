package pl.mleczkobartosz.FootballManager.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Model.FixtureGenerator;
import pl.mleczkobartosz.FootballManager.Model.FootballMatch;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.LeagueRepository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;
    private final ClubRepository clubRepository;

    public LeagueService(LeagueRepository leagueRepository, ClubRepository clubRepository) {
        this.leagueRepository = leagueRepository;
        this.clubRepository = clubRepository;
    }

    public Page<League> findAll(Optional<String> leagueName, Pageable pageable){
        return leagueRepository.findByLeagueName(leagueName.orElse("_"),pageable);
    }

    @Transactional
    public League findById(Long id){
        return leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
    }

    @Transactional
    public League save(League league){
        return leagueRepository.save(league);
    }

    @Transactional
    public League update(Long id, League league){
        League dbLeague = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
        dbLeague.setLeagueName(league.getLeagueName());
        return leagueRepository.save(dbLeague);
    }

    @Transactional
    public String delete(Long id){
        League league = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
        return "League deleted";
    }

    @Transactional
    public Set<Club> findClubs(Long id){
        League league =  leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),id));
        return league.getClubs();
    }



}
