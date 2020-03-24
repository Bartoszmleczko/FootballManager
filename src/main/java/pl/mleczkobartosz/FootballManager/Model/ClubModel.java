package pl.mleczkobartosz.FootballManager.Model;

import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

public class ClubModel {


    private Long club_id;
    @NotNull(message = "Club must have a name")
    @NotEmpty(message = "Club must have a name")
    @NotBlank(message = "Club must have a name")
    @Size(min = 3)
    private String clubName;
    @NotNull(message = "Club must have a foundation year")
    @Min(1850)
    private Integer foundationYear;
    @NotNull(message = "Club must have a manager")
    @Min(0)
    private Long managerId;
    @NotNull(message = "Club must belong to league")
    @Min(0)
    private Long leagueId;
    private Set<Long> players = new HashSet<>();

    public ClubModel(Long club_id, String clubName, Integer foundationYear, Long managerId, Long leagueId) {
        this.club_id = club_id;
        this.clubName = clubName;
        this.foundationYear = foundationYear;
        this.managerId = managerId;
        this.leagueId = leagueId;
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

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getLeagueId() {
        return leagueId;
    }

    public void setLeagueId(Long leagueId) {
        this.leagueId = leagueId;
    }

    public Set<Long> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Long> players) {
        this.players = players;
    }
}
