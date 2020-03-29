package pl.mleczkobartosz.FootballManager.Model;

import pl.mleczkobartosz.FootballManager.Entity.Club;

import java.time.LocalDate;


public class FootballMatch {

    private Club homeTeam;
    private Club awayTeam;
    private LocalDate date;

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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Club getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(Club homeTeam) {
        this.homeTeam = homeTeam;
    }

}
