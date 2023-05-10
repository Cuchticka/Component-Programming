package sk.tuke.gamestudio.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.context.WebApplicationContext;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.game.Field;
import sk.tuke.gamestudio.game.FieldState;
import sk.tuke.gamestudio.services.CommentService;
import sk.tuke.gamestudio.services.RatingException;
import sk.tuke.gamestudio.services.RatingService;
import sk.tuke.gamestudio.services.ScoreService;

import java.util.Date;

@Controller
@RequestMapping("/sliding_puzzle")
@Scope(WebApplicationContext.SCOPE_SESSION)
public class PuzzleController {

    private Field field = new Field(3,3);
    private int zero_row;
    private int zero_column;

    @Autowired
    private ScoreService scoreService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private CommentService commentService;

    @PostMapping("/submitComment")
    public String CommentForm(@RequestParam("name") String name, @RequestParam("comment") String comment) {
        if(name != "" && comment != ""){
            commentService.addComment(new Comment(name,"sliding_puzzle",comment,new Date()));
        }
        return "redirect:/sliding_puzzle/new";
    }

    @PostMapping("/submitRating")
    public String RatingForm(@RequestParam("name") String name, @RequestParam("rating") Integer rating) {
        if(name != "" && rating != null){
            ratingService.setRating(new Rating(name,"sliding_puzzle",rating));
        }
        return "redirect:/sliding_puzzle";
    }


    @PostMapping("/submitScore")
    public String submitForm(@RequestParam("name") String name) {
        if(name!= ""){
            scoreService.addScore(new Score(name,"sliding_puzzle",field.getScore(),new Date()));
        }
        field.setState(FieldState.PLAYING);
        return "redirect:/sliding_puzzle";
    }

    @RequestMapping
    public String sliding_puzzle(@RequestParam(required = false) Integer row, @RequestParam(required = false) Integer column, Model model) {
        if(row != null || column != null){
            if((row + 1 == zero_row && column == zero_column)
                    || (row - 1 == zero_row && column  == zero_column)
                    || (row  == zero_row && column + 1 == zero_column)
                    ||(row  == zero_row && column - 1 == zero_column)){
                if(field.getState() == FieldState.PLAYING){
                    field.SwapTiles(zero_row,zero_column,row,column);
                }
            }
        }

        prepareModel( model);
        return "sliding_puzzle";
    }

    @RequestMapping("/new")
    public String newGame(Model model) {
        field = new Field(3, 3);
        prepareModel( model);
        return "sliding_puzzle";
    }

    public String getState() {
        return field.getState().toString();
    }

    public Integer getAvgRating(){
        return  ratingService.getAverageRating("sliding_puzzle");
    }

    public int getScore(){
        return field.getScore();
    }
    public  String getHtmlField(){

        StringBuilder sb = new StringBuilder();
        sb.append("<table class = 'field'>\n");

        for (int row = 0; row < field.getRowCount(); row++) {
            sb.append("<tr>\n");
            for (int column = 0; column < field.getColumnCount(); column++) {
                sb.append("<td>\n");
                sb.append("<a href='/sliding_puzzle?row=" + row + "&column=" + column + "'>\n");
                sb.append("<img src='/images/" + field.tiles[row][column] + ".jpg'>");
                sb.append("</a>\n");
                sb.append("</td>\n");
                if(field.tiles[row][column] == 0){
                    zero_column = column;
                    zero_row = row;
                }
            }
            sb.append("</tr>\n");
        }
        sb.append("</table>\n");
        return sb.toString();
    }

    private void prepareModel(Model model) {
        model.addAttribute("scores", scoreService.getTopScores("sliding_puzzle"));
        model.addAttribute("comments", commentService.getComments("sliding_puzzle"));
        model.addAttribute("field", field);
    }


}
