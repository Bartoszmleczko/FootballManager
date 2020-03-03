package pl.mleczkobartosz.FootballManager.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mleczkobartosz.FootballManager.Exception.PlayerNotFoundException;

@ControllerAdvice
public class PlayerNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(PlayerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String playerNotFoundHandler(PlayerNotFoundException ex){
        return ex.getMessage();
    }

}
