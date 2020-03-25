package pl.mleczkobartosz.FootballManager.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.FootballManager.Entity.League;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.LeagueRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class LeagueService {

    private final LeagueRepository leagueRepository;

    public LeagueService(LeagueRepository leagueRepository) {
        this.leagueRepository = leagueRepository;
    }

    public Page<League> findAll(Optional<String> leagueName, Pageable pageable){
        return leagueRepository.findByLeagueName(leagueName.orElse("_"),pageable);
    }

    @Transactional
    public League findById(Long id){
        return leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new League(),id));
    }

    @Transactional
    public League save(League league){
        return leagueRepository.save(league);
    }

    @Transactional
    public League update(Long id, League league){
        League dbLeague = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new League(),id));
        dbLeague.setLeagueName(league.getLeagueName());
        return leagueRepository.save(dbLeague);
    }

    @Transactional
    public String delete(Long id){
        League league = leagueRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new League(),id));
        return "League deleted";
    }

}
