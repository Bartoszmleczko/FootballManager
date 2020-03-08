package pl.mleczkobartosz.FootballManager.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "player")
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "player_id")
    private Long player_id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "birth_year")
    private Integer birthYear;
    @Column(name = "market_value")
    private Long marketValue;
    @ManyToOne
    @JoinColumn
    private International international;
    @ManyToOne
    @JoinColumn
    private Club club;
    @OneToMany(mappedBy = "player")
    @JsonBackReference
    private Set<Transfer> transfer;

    public Player(String firstName, String lastName, Integer birthYear, Long marketValue, International international, Club club, Set<Transfer> transfer) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.marketValue = marketValue;
        this.international = international;
        this.club = club;
        this.transfer = transfer;
    }

    public Player() {

    }

    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Long getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Long marketValue) {
        this.marketValue = marketValue;
    }

    public International getInternational() {
        return international;
    }

    public void setInternational(International international) {
        this.international = international;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Set<Transfer> getTransfer() {
        return transfer;
    }

    public void setTransfer(Set<Transfer> transfer) {
        this.transfer = transfer;
    }
}
