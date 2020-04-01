package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.FootballManager.Model.Fixture;

public interface FixtureRepository extends JpaRepository<Fixture, Long> {
}
