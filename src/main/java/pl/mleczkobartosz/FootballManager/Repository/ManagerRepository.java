package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.FootballManager.Entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager,Long> {
}
