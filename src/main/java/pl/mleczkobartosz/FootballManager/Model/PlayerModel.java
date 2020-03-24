package pl.mleczkobartosz.FootballManager.Model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;


public class PlayerModel {


    private Long player_id;

    @NotNull(message = "Player must have first name")
    @NotEmpty(message  = "Player must have first name")
    @NotBlank(message  = "Player must have first name")
    private String firstName;

    @NotNull(message = "Player must have last name")
    @NotEmpty(message  = "Player must have last name")
    @NotBlank(message  = "Player must have last name")
    private String lastName;

    @Min(0)
    private Long marketValue;

    @Min(1950)
    private Integer birthYear;

    @NotNull(message = "Player must belong to some country")
    private Long internationalId;

    @NotNull(message = "Player must have a name")
    private Long clubId;

    private Set<Long> transfers = new HashSet<Long>();

    public PlayerModel() {
    }

    public PlayerModel(@NotNull(message = "Player must have first name") @NotEmpty(message = "Player must have first name") @NotBlank(message = "Player must have first name") String firstName, @NotNull(message = "Player must have last name") @NotEmpty(message = "Player must have last name") @NotBlank(message = "Player must have last name") String lastName, @Min(0) Long marketValue, @Min(1950) Integer birthYear, @NotNull(message = "Player must belong to some country") Long internationalId, @NotNull(message = "Player must have a name") Long clubId, Set<Long> transfers) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.marketValue = marketValue;
        this.birthYear = birthYear;
        this.internationalId = internationalId;
        this.clubId = clubId;
        this.transfers = transfers;
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

    public Long getMarketValue() {
        return marketValue;
    }

    public void setMarketValue(Long marketValue) {
        this.marketValue = marketValue;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Long getInternationalId() {
        return internationalId;
    }

    public void setInternationalId(Long internationalId) {
        this.internationalId = internationalId;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Set<Long> getTransfers() {
        return transfers;
    }

    public void setTransfers(Set<Long> transfers) {
        this.transfers = transfers;
    }
}
