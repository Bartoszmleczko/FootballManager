package pl.mleczkobartosz.FootballManager.Entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long club_id;

    @Column(name = "club_name")
    private String clubName;

    @Column(name="foundation_year")
    private Integer foundationYear;

    @OneToMany(mappedBy = "club", cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Set<Player> players = new HashSet<Player>();

    @OneToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn
    private Manager manager;

    public Club() {
    }

    public Club(String clubName, Integer foundationYear, Set<Player> players, Manager menager) {
        this.clubName = clubName;
        this.foundationYear = foundationYear;
        this.players = players;
        this.manager = menager;
    }

    public Long getClub_id() {
        return club_id;
    }

    public void setClub_id(Long club_id) {
        this.club_id = club_id;
    }

    public String getClubName() {
        return clubName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
    }

    public Integer getFoundationYear() {
        return foundationYear;
    }

    public void setFoundationYear(Integer foundationYear) {
        this.foundationYear = foundationYear;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public Manager getManager() {
        return manager;
    }

    public void setMenager(Manager manager) {
        this.manager = manager;
    }
}
