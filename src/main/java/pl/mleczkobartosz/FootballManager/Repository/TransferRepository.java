package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.mleczkobartosz.FootballManager.Entity.Transfer;

public interface TransferRepository extends JpaRepository<Transfer,Long> {

}
