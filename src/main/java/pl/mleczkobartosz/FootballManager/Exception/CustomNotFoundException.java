package pl.mleczkobartosz.FootballManager.Exception;

public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(String name, Long id) {
        super(name + " with id " + id + " does not exist.");
    }
}
