package pl.mleczkobartosz.FootballManager.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mleczkobartosz.FootballManager.Exception.ClubNotFoundException;

@ControllerAdvice
public class ClubNotFoundAdvice {

    @ResponseBody
    @ExceptionHandler(ClubNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String clubNotFoundHandler(ClubNotFoundException ex){
        return ex.getMessage();
    }


}
