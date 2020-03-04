package pl.mleczkobartosz.FootballManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "league")
public class League {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "league_id")
    private Long league_id;

    @Column(name = "league_name")
    private String leagueName;

    @ManyToMany(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinTable(name="league_club", joinColumns = @JoinColumn(name = "club_id"), inverseJoinColumns = @JoinColumn(name="league_id"))
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
}

