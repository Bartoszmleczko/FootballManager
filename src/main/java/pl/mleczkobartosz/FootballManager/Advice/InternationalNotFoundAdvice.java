package pl.mleczkobartosz.FootballManager.Advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.mleczkobartosz.FootballManager.Exception.InternationalNotFoundException;

@ControllerAdvice
public class InternationalNotFoundAdvice{

    @ResponseBody
    @ExceptionHandler(InternationalNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String internationalNotFoundException(InternationalNotFoundException ex){
        return ex.getMessage();
    }

}
