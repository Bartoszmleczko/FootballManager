package pl.mleczkobartosz.FootballManager.Service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.International;
import pl.mleczkobartosz.FootballManager.Entity.Player;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Model.PlayerModel;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.InternationalRepository;
import pl.mleczkobartosz.FootballManager.Repository.PlayerRepository;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;
    private final ClubRepository clubRepository;
    private final InternationalRepository internationalRepository;

    public PlayerService(PlayerRepository playerRepository, ClubRepository clubRepository, InternationalRepository internationalRepository) {
        this.playerRepository = playerRepository;
        this.clubRepository = clubRepository;
        this.internationalRepository = internationalRepository;
    }

    @Transactional
    public Page<Player> findAll(Optional<String> firstName, Optional<String> lastName, Optional<Integer> birthYear, Pageable pageable){
        if(!birthYear.isPresent())
            return playerRepository.findByFirstNameAndLastName(firstName.orElse("_"),lastName.orElse("_"),pageable);
        return playerRepository.findByFirstNameAndLastNameAndBirthYear(firstName.orElse("_"),lastName.orElse("_"),birthYear.get(),pageable);
    }

    @Transactional
    public Player findById(Long id){
        return playerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Player(),id));
    }

    @Transactional
    public Player save(PlayerModel player){
        Player dbPlayer = new Player();
        Club club = clubRepository.findById(player.getClubId()).orElseThrow(() -> new CustomNotFoundException(new Club(),player.getClubId()));
        International international = internationalRepository.findById(player.getInternationalId()).orElseThrow(() -> new CustomNotFoundException(new International(),player.getInternationalId()));

        dbPlayer.setFirstName(player.getFirstName());
        dbPlayer.setLastName(player.getLastName());
        dbPlayer.setBirthYear(player.getBirthYear());
        dbPlayer.setMarketValue(player.getMarketValue());
        dbPlayer.setClub(club);
        dbPlayer.setInternational(international);

        return playerRepository.save(dbPlayer);
    }

    @Transactional
    public Player update (Long id, PlayerModel player){
        Player dbPlayer = playerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Player(),id));
        Club club = clubRepository.findById(player.getClubId()).orElseThrow(() -> new CustomNotFoundException(new Club(),player.getClubId()));
        International international = internationalRepository.findById(player.getInternationalId()).orElseThrow(() -> new CustomNotFoundException(new International(),player.getInternationalId()));

        dbPlayer.setFirstName(player.getFirstName());
        dbPlayer.setLastName(player.getLastName());
        dbPlayer.setBirthYear(player.getBirthYear());
        dbPlayer.setMarketValue(player.getMarketValue());
        dbPlayer.setClub(club);
        dbPlayer.setInternational(international);
        return playerRepository.save(dbPlayer);
    }

    @Transactional
    public String deletePlayer(@PathVariable Long id){
        Player player = playerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Player(),id));
        playerRepository.delete(player);
        return "Player deleted";
    }

}
