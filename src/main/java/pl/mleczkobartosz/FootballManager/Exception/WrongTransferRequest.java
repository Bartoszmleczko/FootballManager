package pl.mleczkobartosz.FootballManager.Exception;

public class WrongTransferRequest  extends RuntimeException{

    public WrongTransferRequest() {
        super("Wrong transfer request.");
    }
}
