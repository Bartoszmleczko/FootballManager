package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.FootballManager.Entity.Player;

public interface PlayerRepository extends JpaRepository<Player,Long> {
}
