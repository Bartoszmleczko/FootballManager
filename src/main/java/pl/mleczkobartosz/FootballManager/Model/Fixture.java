package pl.mleczkobartosz.FootballManager.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Fixture {

    private Long fixtureNo;
    private List<FootballMatch> matches = new ArrayList<>();
    private LocalDateTime fixtureStart;

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
}
