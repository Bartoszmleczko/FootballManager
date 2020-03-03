package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mleczkobartosz.FootballManager.Entity.International;

public interface InternationalRepository extends JpaRepository<International,Long> {

    @Query("SELECT i  FROM International i where internationalName like %?1%")
    public Page<International> findInternationalByName(String name, Pageable pageable);

}
