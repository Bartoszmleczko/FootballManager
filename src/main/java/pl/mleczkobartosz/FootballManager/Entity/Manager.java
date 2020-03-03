package pl.mleczkobartosz.FootballManager.Entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Table(name="manager")
public class Manager {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "menager_id")
    private Long menager_id;

    @Column(name = "first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="birth_year")
    private Integer birthYear;

    @OneToOne(mappedBy = "manager",cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    private Club club;

    public Manager() {
    }

    public Manager(String firstName, String lastName, Integer birthYear, Club club) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthYear = birthYear;
        this.club = club;
    }

    public Long getMenager_id() {
        return menager_id;
    }

    public void setMenager_id(Long menager_id) {
        this.menager_id = menager_id;
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

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }
}
