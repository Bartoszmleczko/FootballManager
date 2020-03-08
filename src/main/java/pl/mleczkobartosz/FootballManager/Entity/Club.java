package pl.mleczkobartosz.FootballManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "club")
public class Club {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "club_id")
    private Long club_id;

    @NotNull(message = "Club must have a name")
    @NotEmpty(message = "Club must have a name")
    @NotBlank(message = "Club must have a name")
    @Size(min = 3)
    @Column(name = "club_name")
    private String clubName;

    @Min(1850)
    @NotNull(message = "Club must have a foundation year")
    @Column(name="foundation_year")
    private Integer foundationYear;

    @OneToMany(mappedBy = "club")
    @JsonBackReference("players")
    private Set<Player> players = new HashSet<Player>();

    @NotNull(message = "Club must have manager")
    @OneToOne
    @JoinColumn
    private Manager manager;

    @NotNull(message = "Club must belong to the league")
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    @JoinColumn
    private League league;

    @OneToMany(mappedBy = "buyingClub")
    @JsonBackReference("incomeTransfer")
    private Set<Transfer> incomeTransfers = new HashSet<Transfer>();

    @OneToMany(mappedBy = "sellingClub")
    @JsonBackReference("outcomeTransfer")
    private Set<Transfer> outcomeTransfers = new HashSet<Transfer>();

    public Club() {
    }

    public Club(String clubName, Integer foundationYear, Set<Player> players, Manager manager, League league) {
        this.clubName = clubName;
        this.foundationYear = foundationYear;
        this.players = players;
        this.manager = manager;
        this.league = league;
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

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public League getLeague() {
        return league;
    }

    public void setLeague(League league) {
        this.league = league;
    }

    public Set<Transfer> getIncomeTransfers() {
        return incomeTransfers;
    }

    public void setIncomeTransfers(Set<Transfer> incomeTransfers) {
        this.incomeTransfers = incomeTransfers;
    }

    public Set<Transfer> getOutcomeTransfers() {
        return outcomeTransfers;
    }

    public void setOutcomeTransfers(Set<Transfer> outcomeTransfers) {
        this.outcomeTransfers = outcomeTransfers;
    }
}

