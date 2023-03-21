package sk.tuke.gamestudio;

import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.services.RatingService;
import sk.tuke.gamestudio.services.RatingServiceJDBC;
import sk.tuke.gamestudio.services.ScoreService;
import sk.tuke.gamestudio.services.ScoreServiceJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLOutput;
import java.util.Date;

public class TestJDBC {
    public static void main(String[] args) throws Exception{
        Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost/gamestudio", "postgres", "kukurica28");
        RatingService ratingService = new RatingServiceJDBC();
        ratingService.reset();
        ratingService.setRating(new Rating("Ferko","sliding puzzle",3));
        ratingService.setRating(new Rating("Ferko","sliding puzzle",5));
        ratingService.setRating(new Rating("Jozko","sliding puzzle",1));
        ratingService.setRating(new Rating("Eliska","sliding puzzle",1));
        ratingService.setRating(new Rating("Dominicka","sliding puzzle",1));
        System.out.printf("rating ferka: %d\n",ratingService.getRating("sliding puzzle","Ferko"));
        System.out.printf("rating jozka: %d\n",ratingService.getRating("sliding puzzle","Jozko"));
        System.out.printf("average rating: %d\n",ratingService.getAverageRating("sliding puzzle"));

        /*
        ScoreService service = new ScoreServiceJDBC();
        service.reset();
        service.addScore(new Score("sliding puzzle","betik", 69, new Date()));
        service.addScore(new Score("sliding puzzle","domco", 1337, new Date()));
        service.addScore(new Score("sliding puzzle","minko", 420, new Date()));
        System.out.println(service.getTopScores("sliding puzzle"));
        */
    }
}
