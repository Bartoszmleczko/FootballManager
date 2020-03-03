package pl.mleczkobartosz.FootballManager.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pl.mleczkobartosz.FootballManager.Entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager,Long> {

    @Query("select m from Manager m where firstName like %?1% and lastName like %?2%")
    public Page<Manager> findManagerByFirstNameAndLastName(String firstName, String lastName, Pageable pageable);

    @Query("select m from Manager m where firstName like %?1% and lastName like %?2% and birthYear like ?3")
    public Page<Manager> findManagerByFirstNameAndLastNameAndBirthYear(String firstName, String lastName, Integer year, Pageable pageable);

}
