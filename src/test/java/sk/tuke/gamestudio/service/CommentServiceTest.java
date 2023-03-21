package sk.tuke.gamestudio.service;

import org.junit.jupiter.api.Test;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.services.CommentService;
import sk.tuke.gamestudio.services.CommentServiceJDBC;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommentServiceTest {

    private CommentService commentService = new CommentServiceJDBC();

    @Test
    public void reset(){
        commentService.reset();
        assertEquals(0,commentService.getComments("sliding puzzle").size());
    }

    @Test
    public void addComment(){
        commentService.reset();
        var date = new Date();

        commentService.addComment(new Comment("Nora Mojsejova","sliding puzzle","nedala som sa ojebat",date));

        var comments = commentService.getComments("sliding puzzle");
        assertEquals(1,comments.size());
        assertEquals("sliding puzzle", comments.get(0).getGame());
        assertEquals("Nora Mojsejova",comments.get(0).getPlayer());
        assertEquals("nedala som sa ojebat",comments.get(0).getComment());
        assertEquals(date,comments.get(0).getCommentedOn());
    }

    @Test
    public void getComments(){
        commentService.reset();
        var date = new Date();
        commentService.addComment(new Comment("neRytmus","sliding puzzle", "si NEzabil",date));
        commentService.addComment(new Comment("Rytmus","sliding puzzle", "si zabil", new Date(2002-1900, Calendar.MAY,5)));
        commentService.addComment(new Comment("Alenka","sliding puzzle", "risa divov", new Date(2002-1900,Calendar.APRIL,30)));
        var comments = commentService.getComments("sliding puzzle");
        assertEquals(3,comments.size());

        assertEquals("neRytmus",comments.get(0).getPlayer());
        assertEquals("sliding puzzle",comments.get(0).getGame());
        assertEquals("si NEzabil",comments.get(0).getComment());
        assertEquals(date, comments.get(0).getCommentedOn());

        assertEquals("Rytmus",comments.get(1).getPlayer());

        assertEquals("Alenka",comments.get(2).getPlayer());
    }
}
