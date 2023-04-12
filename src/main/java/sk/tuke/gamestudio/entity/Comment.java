package sk.tuke.gamestudio.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

import java.util.Date;

@NamedQuery( name = "Comment.getComments",
        query = "SELECT c FROM Comment c WHERE c.game=:game ORDER BY c.commentedOn DESC")
@NamedQuery( name = "Comment.reset",
        query = "DELETE FROM Comment ")
@Entity
public class Comment {

    @Id
    @GeneratedValue
    private int ident;
    private String game;

    private String player;

    private String comment;

    private Date commentedOn;


    public Comment(String player, String game, String comment, Date commentedOn) {
        this.game = game;
        this.player = player;
        this.comment = comment;
        this.commentedOn = commentedOn;
    }

    public Comment() {}

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getComment() {
        return comment;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public String getGame() {
        return game;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public void setCommentedOn(Date commentedOn) {
        this.commentedOn = commentedOn;
    }

    public Date getCommentedOn() {
        return commentedOn;
    }

    @Override
    public String toString() {
        return "Score{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", comment=" + comment +
                ", commentedOn=" + commentedOn +
                '}';
    }

    public int getIdent() {
        return ident;
    }

    public void setIdent(int ident) {
        this.ident = ident;
    }
}
