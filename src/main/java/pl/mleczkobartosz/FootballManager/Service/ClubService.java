package pl.mleczkobartosz.FootballManager.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;
import pl.mleczkobartosz.FootballManager.Entity.Manager;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Exception.ManagerAlreadyHasAClubException;
import pl.mleczkobartosz.FootballManager.Model.ClubModel;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.LeagueRepository;
import pl.mleczkobartosz.FootballManager.Repository.ManagerRepository;
import pl.mleczkobartosz.FootballManager.Repository.PlayerRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class ClubService {

    private final ClubRepository clubRepository;
    private final LeagueRepository leagueRepository;
    private final ManagerRepository managerRepository;

    public ClubService(ClubRepository clubRepository, LeagueRepository leagueRepository, ManagerRepository managerRepository) {
        this.clubRepository = clubRepository;
        this.leagueRepository = leagueRepository;
        this.managerRepository = managerRepository;
    }

    @Transactional
    public Page<Club> findAll(Optional<String> name, Optional<Integer> year, Pageable pageable){
        if(!year.isPresent())
            return clubRepository.findByClubName(name.orElse("_"),pageable);

        return clubRepository.findByClubNameAndFoundationYear(name.orElse("_"),year.get(),pageable);
    }

    @Transactional
    public Club findById(Long id){
        return clubRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(Club.class.getSimpleName(),id));
    }

    @Transactional
    public Club save(ClubModel clubModel){

        Club club =  new Club();
        League league = leagueRepository.findById(clubModel.getLeagueId()).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(),clubModel.getLeagueId()));
        Manager manager = managerRepository.findById(clubModel.getManagerId()).orElseThrow(() -> new CustomNotFoundException(Manager.class.getSimpleName(),clubModel.getManagerId()));

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

    @Transactional
    public Club update(Long id, ClubModel clubModel){
        Club dbClub = clubRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(Club.class.getSimpleName(),id));
        dbClub.setClubName(clubModel.getClubName());
        dbClub.setFoundationYear(clubModel.getFoundationYear());
        dbClub.setMenager( managerRepository.findById(clubModel.getManagerId()).orElseThrow(() -> new CustomNotFoundException(Manager.class.getSimpleName(),clubModel.getManagerId())));
        dbClub.setLeague( leagueRepository.findById( clubModel.getLeagueId()).orElseThrow(() -> new CustomNotFoundException(League.class.getSimpleName(), clubModel.getLeagueId())) );
        return clubRepository.save(dbClub);
    }


    @Transactional
    public String delete(Long id ){
        Club dbClub = clubRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(Club.class.getSimpleName(),id));
        clubRepository.delete(dbClub);
        return "Club deleted";
    }
}
