package pl.mleczkobartosz.FootballManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "international")
public class International {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "international_id")
    private Long international_id;

    @Column(name = "international_name")
    private String internationalName;

    @OneToMany(mappedBy = "international",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JsonBackReference
    private Set<Player> players = new HashSet<Player>();

    public International() {
    }

    public International(String internationalName, Set<Player> players) {
        this.internationalName = internationalName;
        this.players = players;
    }

    public Long getInternational_id() {
        return international_id;
    }

    public void setInternational_id(Long international_id) {
        this.international_id = international_id;
    }

    public String getInternationalName() {
        return internationalName;
    }

    public void setInternationalName(String internationalName) {
        this.internationalName = internationalName;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }
}
