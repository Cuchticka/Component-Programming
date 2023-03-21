package sk.tuke.gamestudio.entity;


public class Rating {

    private String player;

    private String game;

    private int rating;

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
}
