package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.International;
import pl.mleczkobartosz.FootballManager.Entity.Player;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Model.PlayerModel;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;
import pl.mleczkobartosz.FootballManager.Repository.InternationalRepository;
import pl.mleczkobartosz.FootballManager.Repository.PlayerRepository;
import pl.mleczkobartosz.FootballManager.Service.PlayerService;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public Page<Player> findAll(Optional<String> firstName, Optional<String> lastName, Optional<Integer> birthYear, Pageable pageable){
            return playerService.findAll(firstName,lastName,birthYear,pageable);
    }

    @GetMapping("/players/{id}")
    public Player findPlayerById(@PathVariable Long id){
        return playerService.findById(id);
    }

    @PostMapping("/players")
    public Player savePlayer(@Valid @RequestBody PlayerModel player){

        return playerService.save(player);
    }

    @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable Long id,@Valid @RequestBody PlayerModel player){
            return playerService.update(id,player);
    }

    @DeleteMapping("/players/{id}")
    public String deletePlayer(@PathVariable Long id){
        return playerService.deletePlayer(id);
    }


}
