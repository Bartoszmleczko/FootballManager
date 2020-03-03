package pl.mleczkobartosz.FootballManager.Exception;

public class InternationalNotFoundException extends RuntimeException {
    public InternationalNotFoundException(Long id) {
        super("International with id " + id + " does not exist");
    }
}
