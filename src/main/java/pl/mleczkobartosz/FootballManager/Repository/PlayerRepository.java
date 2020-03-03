package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mleczkobartosz.FootballManager.Entity.Player;

public interface PlayerRepository extends JpaRepository<Player,Long> {

    @Query("select p from Player p where firstName like %?1% and lastName like %?2%")
    public Page<Player> findByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

    @Query("select p from Player p where firstName like %?1% and lastName like %?2% and birthYear like ?3")
    public Page<Player> findByFirstNameAndLastNameAndBirthYear(String firstName, String lastName, Integer birthYear, Pageable pageable);


}
