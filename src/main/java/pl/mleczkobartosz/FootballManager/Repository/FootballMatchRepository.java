package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.FootballManager.Model.FootballMatch;

public interface FootballMatchRepository extends JpaRepository<FootballMatch, Long> {
}
