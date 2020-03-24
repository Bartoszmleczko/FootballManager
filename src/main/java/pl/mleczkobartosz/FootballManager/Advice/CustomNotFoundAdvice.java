package pl.mleczkobartosz.FootballManager.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mleczkobartosz.FootballManager.Exception.CustomNotFoundException;
import pl.mleczkobartosz.FootballManager.Exception.ManagerAlreadyHasAClubException;

@ControllerAdvice
public class CustomNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(CustomNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String notFoundHandler(CustomNotFoundException ex){
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(ManagerAlreadyHasAClubException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    String hasAClubHandler(ManagerAlreadyHasAClubException ex) {
        return ex.getMessage();
    }
}
