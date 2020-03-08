package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Player;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.PlayerRepository;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PlayerController {

    private final PlayerRepository playerRepository;

    public PlayerController(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @GetMapping("/players")
    public Page<Player> findAll(Optional<String> firstName, Optional<String> lastName, Optional<Integer> birthYear, Pageable pageable){
        if(!birthYear.isPresent())
            return playerRepository.findByFirstNameAndLastName(firstName.orElse("_"),lastName.orElse("_"),pageable);
        return playerRepository.findByFirstNameAndLastNameAndBirthYear(firstName.orElse("_"),lastName.orElse("_"),birthYear.get(),pageable);
    }

    @GetMapping("/players/{id}")
    public Player findPlayerById(@PathVariable Long id){
        return playerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Player(),id));
    }

    @PostMapping("/players")
    public Player savePlayer(@Valid @RequestBody Player player){
        return playerRepository.save(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable Long id,@Valid @RequestBody Player player){
        Player dbPlayer = playerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Player(),id));
        dbPlayer.setFirstName(player.getFirstName());
        dbPlayer.setLastName(player.getLastName());
        dbPlayer.setBirthYear(player.getBirthYear());
        if(player.getClub()!=null)
        dbPlayer.setClub(player.getClub());
        if(player.getInternational()!=null)
        dbPlayer.setInternational(player.getInternational());
        dbPlayer.setMarketValue(player.getMarketValue());
        return playerRepository.save(dbPlayer);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable Long id){
        Player player = playerRepository.findById(id).orElseThrow(() -> new CustomNotFoundException(new Player(),id));
        playerRepository.delete(player);
        return "Player deleted";
    }


}
