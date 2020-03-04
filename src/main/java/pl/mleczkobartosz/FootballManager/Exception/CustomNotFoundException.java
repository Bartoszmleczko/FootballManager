package pl.mleczkobartosz.FootballManager.Exception;

public class CustomNotFoundException extends RuntimeException {

    public CustomNotFoundException(Object object, Long id) {
        super(object.getClass().getSimpleName() + " with id " + id + " does not exist.");
    }
}
