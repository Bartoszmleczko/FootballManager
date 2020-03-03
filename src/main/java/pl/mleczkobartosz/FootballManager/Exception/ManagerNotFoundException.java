package pl.mleczkobartosz.FootballManager.Exception;

public class ManagerNotFoundException extends RuntimeException {

    public ManagerNotFoundException(Long id) {
        super("Manager with id" + id + "does not exist");
    }
}
