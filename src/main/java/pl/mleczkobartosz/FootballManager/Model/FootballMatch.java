package pl.mleczkobartosz.FootballManager.Model;

import pl.mleczkobartosz.FootballManager.Entity.Club;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "football_match")
public class FootballMatch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "match_id")
    private Long match_id;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Club homeTeam;
    @ManyToOne(cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.REFRESH})
    private Club awayTeam;
    @ManyToOne
    private Fixture fixture;
    @Column(name = "match_date")
    private LocalDateTime date;

    public FootballMatch() {
    }

    public FootballMatch(Club homeTeam, Club awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }


    public Club getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(Club awayTeam) {
        this.awayTeam = awayTeam;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Club getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Club homeTeam) {
        this.homeTeam = homeTeam;
    }

}
