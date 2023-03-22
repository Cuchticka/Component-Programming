package sk.tuke.gamestudio.services;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;

import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingServiceJDBC implements RatingService{

    public static final String URL = "jdbc:postgresql://localhost/gamestudio";
    public static final String USER = "postgres";
    public static final String PASSWORD = "kukurica28";

    public static final String SELECT = "SELECT rating FROM ratings WHERE game = ? AND player = ?";
    public static final String DELETE = "DELETE FROM ratings";
    public static final String INSERT = "INSERT INTO ratings(player, game, rating) VALUES (?,?,?)";

    public static final String UPDATE = "UPDATE ratings SET rating = ? WHERE player = ? AND game = ?";

    public static final String SELECT1 = "SELECT player FROM ratings WHERE player = ? AND game = ?";

    public static final String SELECT2 = "SELECT AVG(rating) FROM ratings WHERE game = ?";


    @Override
    public void setRating(Rating rating){
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement select = connection.prepareStatement(SELECT1);
        ){
            select.setString(1, rating.getPlayer());
            select.setString(2,rating.getGame());
            try(ResultSet rs = select.executeQuery()) {
                List<String> ratings = new ArrayList<>();
                while (rs.next()){
                    ratings.add(rs.getString(1));
                }
                if(ratings.size()==0){
                    PreparedStatement statement = connection.prepareStatement(INSERT);
                    statement.setString(1,rating.getPlayer());
                    statement.setString(2,rating.getGame());
                    statement.setInt(3,rating.getRating());
                    statement.executeUpdate();
                }
                else{
                    PreparedStatement update = connection.prepareStatement(UPDATE);
                    update.setInt(1,rating.getRating());
                    update.setString(2,rating.getPlayer());
                    update.setString(3, rating.getGame());
                    update.executeUpdate();
                }
            }

        }
        catch (SQLException e){
            throw new RatingException("Problem seting rating");
        }
    }

    @Override
    public int getAverageRating(String game) {
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT2);
            ) {
            statement.setString(1,game);
            int count = 0;
            try(ResultSet rs = statement.executeQuery()) {
                rs.next();
                count = rs.getInt(1);

            }
            return  count;

        }
        catch (SQLException e){
            throw new RatingException("Problem getting average rating", e);
        }

    }

    @Override
    public int getRating(String game, String player){
        try(Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
            PreparedStatement statement = connection.prepareStatement(SELECT);
        ) {
            statement.setString(1,game);
            statement.setString(2,player);
            int count = 0;
            try(ResultSet rs = statement.executeQuery()) {
                if(rs.next()){
                    count = rs.getInt(1);
                    return  count;
                }
                else{
                    return 0;
                }

            }


        }
        catch (SQLException e){
            throw new RatingException("Problem getting rating", e);
        }
    }

    @Override
    public void reset() throws RatingException {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
        ) {
            statement.executeUpdate(DELETE);
        } catch (SQLException e) {
            throw new RatingException("Problem deleting rating", e);
        }
    }
}
