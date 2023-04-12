package sk.tuke.gamestudio;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.game.Field;
import sk.tuke.gamestudio.game.Puzzle;
import sk.tuke.gamestudio.services.*;
import sk.tuke.gamestudio.ui.ConsoleUI;

import java.awt.*;

@SpringBootApplication
@Configuration
public class SpringClient {
    public static void main(String[] args) {
        new SpringApplicationBuilder(SpringClient.class).web(WebApplicationType.NONE).run(args);
    }

    @Bean
    public CommandLineRunner runner(){
        return s ->{
            ui().menu();
        };

    }

    @Bean
    public ConsoleUI ui(){
        return new ConsoleUI(new Field(3,3));
    }


    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ScoreService scoreService(){
        return new ScoreServiceRestClient();
    }

    @Bean
    public RatingService ratingService(){return new RatingServiceJPA();}

    @Bean
    public CommentService commentService(){return new CommentServiceJPA();}

}
