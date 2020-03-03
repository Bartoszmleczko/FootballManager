package pl.mleczkobartosz.FootballManager.Exception;

public class ClubNotFoundException extends RuntimeException{

    public ClubNotFoundException(Long id) {
        super("Club with id "+ id + "does not exist");
    }
}
