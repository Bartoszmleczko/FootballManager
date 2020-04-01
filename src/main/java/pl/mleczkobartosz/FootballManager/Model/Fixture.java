package pl.mleczkobartosz.FootballManager.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import pl.mleczkobartosz.FootballManager.Entity.League;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fixture")
public class Fixture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fixture_id")
    private Long fixture_id;
    @Column(name="fixture_no")
    private Long fixtureNo;

    @OneToMany(mappedBy = "fixture")
    @JsonBackReference("matches")
    private List<FootballMatch> matches = new ArrayList<>();

    @ManyToOne
    private League league;

    @Column(name = "fixture_start")
    private LocalDateTime fixtureStart;

    @Column(name = "fixture_end")
    private LocalDateTime fixtureEnd;

    public Fixture() {
    }

    public Long getFixtureNo() {
        return fixtureNo;
    }

    public void setFixtureNo(Long fixtureNo) {
        this.fixtureNo = fixtureNo;
    }

    public List<FootballMatch> getMatches() {
        return matches;
    }

    public void setMatches(List<FootballMatch> matches) {
        this.matches = matches;
    }

    public LocalDateTime getFixtureStart() {
        return fixtureStart;
    }

    public void setFixtureStart(LocalDateTime fixtureStart) {
        this.fixtureStart = fixtureStart;
    }

    public LocalDateTime getFixtureEnd() {
        return fixtureEnd;
    }

    public void setFixtureEnd(LocalDateTime fixtureEnd) {
        this.fixtureEnd = fixtureEnd;
    }
}
