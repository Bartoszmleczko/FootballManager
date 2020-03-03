package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.FootballManager.Entity.International;

public interface InternationalRepository extends JpaRepository<International,Long> {
}
