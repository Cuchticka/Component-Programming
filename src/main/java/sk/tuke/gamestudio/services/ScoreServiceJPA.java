package sk.tuke.gamestudio.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Score;

import java.util.List;

@Transactional
public class ScoreServiceJPA implements ScoreService{
    @PersistenceContext
    private EntityManager entity;

    @Override
    public void addScore(Score score) throws ScoreException {
        entity.persist(score);
    }

    @Override
    public List<Score> getTopScores(String game) throws ScoreException {
        return entity.createNamedQuery("Score.getTopScores")
        .setParameter("game",game)
        .setMaxResults(10)
        .getResultList();
    }

    @Override
    public void reset() throws ScoreException {
        entity.createNamedQuery("Score.reset").executeUpdate();
    }
}
