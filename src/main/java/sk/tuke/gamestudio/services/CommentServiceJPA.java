package sk.tuke.gamestudio.services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import sk.tuke.gamestudio.entity.Comment;

import java.util.List;

@Transactional
public class CommentServiceJPA implements CommentService{

    @PersistenceContext
    private EntityManager entity;
    @Override
    public void addComment(Comment comment) throws CommentException {
        entity.persist(comment);
    }

    @Override
    public List<Comment> getComments(String game) throws CommentException {
        return entity.createNamedQuery("Comment.getComments").setParameter("game", game).getResultList();
    }

    @Override
    public void reset() throws CommentException {
        entity.createNativeQuery("Comment.reset").executeUpdate();
    }
}
