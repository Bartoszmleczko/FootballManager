package pl.mleczkobartosz.FootballManager.Exception;

public class ManagerAlreadyHasAClubException extends RuntimeException {

    public ManagerAlreadyHasAClubException(Long id) {
        super("Manager with id " + id + " already has a club.");
    }
}
