package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.FootballManager.Entity.Club;

public interface ClubRepository extends JpaRepository<Club,Long> {
}
