package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;

public interface ClubRepository extends JpaRepository<Club,Long> {

    @Query("SELECT c FROM Club c where clubName like %?1%")
    public Page<Club> findByClubName(String name, Pageable pageable);

    @Query("SELECT c FROM Club c where clubName like %?1% and foundationYear like %?2% ")
    public Page<Club> findByClubNameAndFoundationYear(String name, Integer year, Pageable pageable);

}
