package sk.tuke.gamestudio.services;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Rating;

@Transactional
public class RatingServiceJPA implements RatingService{

    @PersistenceContext
    private EntityManager entity;
    @Override
    public void setRating(Rating rating) throws RatingException {
        entity.persist(rating);
    }

    @Override
    public int getAverageRating(String game){
            var querry =  entity.createNamedQuery("Rating.getAverageRating")
                    .setParameter("game",game);
            if(querry.getSingleResult()==null){
                return -1;
            }
            else {
                return (int) Math.round((double)querry.getSingleResult());
            }
    }

   //SELECT player FROM ratings WHERE player = ? AND game = ?
    @Override
    public int getRating(String game, String player) {
        try{
            return (int) entity.createNamedQuery("Rating.getRating")
                    .setParameter("game", game)
                    .setParameter("player", player)
                    .getSingleResult();
        }
        catch (NoResultException e){
            return -1;
        }
    }



    @Override
    public void reset() throws RatingException {
        entity.createNamedQuery("Rating.reset").executeUpdate();
    }
}
