package pl.mleczkobartosz.FootballManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pl.mleczkobartosz.FootballManager.Model.Fixture;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private Long league_id;

    @NotNull(message = "Club must have a name")
    @NotEmpty(message = "Club must have a name")
    @NotBlank(message = "Club must have a name")
    @Size(min = 3)
    @Column(name = "league_name")
    private String leagueName;

    @OneToMany(mappedBy = "league")
    @JsonBackReference
    private List<Fixture> fixtures = new ArrayList<>();

    @OneToMany(mappedBy = "league")
    @JsonBackReference
    private Set<Club> clubs = new HashSet<Club>();

    public League() {
    }

    public League(String leagueName, Set<Club> clubs) {
        this.leagueName = leagueName;
        this.clubs = clubs;
    }

    public Long getLeague_id() {
        return league_id;
    }

    public void setLeague_id(Long laegue_id) {
        this.league_id = laegue_id;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public Set<Club> getClubs() {
        return clubs;
    }

    public void setClubs(Set<Club> clubs) {
        this.clubs = clubs;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }
}

