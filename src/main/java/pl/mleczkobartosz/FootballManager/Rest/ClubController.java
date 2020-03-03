package pl.mleczkobartosz.FootballManager.Rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Exception.ClubNotFoundException;
import pl.mleczkobartosz.FootballManager.Repository.ClubRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class ClubController {

    private final ClubRepository clubRepository;

    public ClubController(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @GetMapping("/clubs")
    public Page<Club> findAll(Optional<String> name, Optional<Integer> year, Pageable pageable){
        if(!year.isPresent())
            return clubRepository.findByClubName(name.orElse("_"),pageable);

        return clubRepository.findByClubNameAndFoundationYear(name.orElse("_"),year.get(),pageable);
    }

    @GetMapping("/clubs/{id}")
    public Club getClub(@PathVariable Long id){
        return clubRepository.findById(id).orElseThrow(() -> new ClubNotFoundException(id));
    }

    @PostMapping("/clubs")
    public Club saveClub(@RequestBody Club club){
        return clubRepository.save(club);
    }

    @PutMapping("/clubs/{id}")
    public Club updateClub(@PathVariable Long id, @RequestBody Club club){
        Club dbClub = clubRepository.findById(id).orElseThrow(() -> new ClubNotFoundException(id));
        dbClub.setClubName(club.getClubName());
        dbClub.setFoundationYear(club.getFoundationYear());
        dbClub.setMenager(club.getManager());
        dbClub.setPlayers(club.getPlayers());
        return clubRepository.save(dbClub);
    }

    @DeleteMapping("/clubs/{id}")
    public String deleteClub(@PathVariable Long id){
        Club dbClub = clubRepository.findById(id).orElseThrow(() -> new ClubNotFoundException(id));
        clubRepository.delete(dbClub);
        return "Club deleted";
    }


}
