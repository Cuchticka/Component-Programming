package sk.tuke.gamestudio.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import sk.tuke.gamestudio.entity.Rating;

import java.util.Arrays;

public class RatingServiceRestClient implements RatingService{

    private final String url = "http://localhost:8080/api/rating";
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public void setRating(Rating rating){
        restTemplate.postForEntity(url, rating, Rating.class);
    }

    @Override
    public int getAverageRating(String game) {
        if(restTemplate.getForEntity(url + "/" + game, Integer.class).getBody() ==null){
            return 0;
        }
        return restTemplate.getForEntity(url + "/" + game, Integer.class).getBody().intValue();
    }

    @Override
    public int getRating(String game, String player){
        if(restTemplate.getForEntity(url+ "/"+game+"/"+player, Integer.class).getBody() == null){
            return 0;
        }
        else{
            return restTemplate.getForEntity(url+ "/"+game+"/"+player, Integer.class).getBody();
        }

    }

    @Override
    public void reset() throws RatingException {

    }
}
