package sk.tuke.gamestudio.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;

//SELECT AVG(rating) FROM ratings WHERE game = ?
@NamedQuery( name = "Rating.getAverageRating",
        query = "SELECT AVG(r.rating)  FROM Rating r WHERE r.game=:game")
@NamedQuery( name = "Rating.reset",
        query = "DELETE FROM Rating")

@NamedQuery(name = "Rating.getRating",
        query = "SELECT r.rating FROM Rating r WHERE r.player=:player AND r.game=:game")
@Entity
public class Rating {
    @Id
    @GeneratedValue
    private int ident;
    private String player;

    private String game;

    private int rating;

    public Rating(){}

    public Rating(String player,String game,int rating){
        this.player = player;
        this.game = game;
        this.rating = rating;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return player;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "player='" + player + '\'' +
                ", game='" + game + '\'' +
                ", rating=" + rating +
                '}';
    }
    public int getIdent() { return ident; }
    public void setIdent(int ident) { this.ident = ident; }
}
