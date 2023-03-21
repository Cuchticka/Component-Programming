package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.services.RatingService;
import sk.tuke.gamestudio.services.RatingServiceJDBC;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RatingServiceTest {

    private RatingService ratingService = new RatingServiceJDBC();


    @Test
    public void getAverageRating(){
        ratingService.reset();
        ratingService.setRating(new Rating("Ferko","sliding puzzle",3));
        ratingService.setRating(new Rating("Ferko","sliding puzzle",5));
        ratingService.setRating(new Rating("Jozko","sliding puzzle",1));
        ratingService.setRating(new Rating("Eliska","sliding puzzle",1));
        ratingService.setRating(new Rating("Domco","sliding puzzle",1));
        ratingService.setRating(new Rating("Dominicka","mines",5));
        ratingService.setRating(new Rating("Minko","mines",1));
        var rating = ratingService.getAverageRating("mines");
        assertEquals(3,rating);
        rating = ratingService.getAverageRating("sliding puzzle");
        assertEquals(2,rating);
    }

    @Test
    public void reset(){
        ratingService.reset();
        assertEquals(0,ratingService.getRating("sliding puzzle","Fero"));
    }

    @Test
    public void setRatingService(){
        ratingService.reset();
        ratingService.setRating(new Rating("Betka", "sliding puzzle",1));
        ratingService.setRating(new Rating("Betka", "mines",3));
        ratingService.setRating(new Rating("Domco", "sliding puzzle",5));
        ratingService.setRating(new Rating("Domco", "sliding puzzle",2));
        var rating = ratingService.getRating("sliding puzzle", "Betka");
        assertEquals(1,rating);
        rating = ratingService.getRating("mines","Betka");
        assertEquals(3,rating);
        rating = ratingService.getRating("sliding puzzle","Domco");
        assertEquals(2,rating);

    }

    @Test
    public void getRatingService(){
        ratingService.reset();
        ratingService.setRating(new Rating("Ferko","sliding puzzle",3));
        ratingService.setRating(new Rating("Ferko","sliding puzzle",5));
        ratingService.setRating(new Rating("Jozko","sliding puzzle",1));
        ratingService.setRating(new Rating("Eliska","sliding puzzle",2));
        ratingService.setRating(new Rating("Dominicka","mines",4));
        var ratings = ratingService.getRating("sliding puzzle", "Ferko");
        assertEquals(5,ratings);
        ratings = ratingService.getRating("sliding puzzle", "Eliska");
        assertEquals(2,ratings);
        ratings = ratingService.getRating("mines", "Dominicka");
        assertEquals(4,ratings);
    }

}
