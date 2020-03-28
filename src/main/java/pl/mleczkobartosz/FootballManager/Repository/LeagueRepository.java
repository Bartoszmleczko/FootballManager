package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mleczkobartosz.FootballManager.Entity.Club;
import pl.mleczkobartosz.FootballManager.Entity.League;

public interface LeagueRepository extends JpaRepository<League,Long> {

    @Query("select league from League league where leagueName like %?1%")
    public Page<League> findByLeagueName(String name, Pageable pageable);




}
