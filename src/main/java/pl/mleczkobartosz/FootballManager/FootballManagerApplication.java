package pl.mleczkobartosz.FootballManager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.mleczkobartosz.FootballManager.Model.FixtureGenerator;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class FootballManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FootballManagerApplication.class, args);
	}
	
}
