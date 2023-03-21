package sk.tuke.gamestudio;

import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Field;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.services.*;
import sk.tuke.gamestudio.ui.ConsoleUI;

import java.util.Date;
import java.util.Scanner;


public class Puzzle {

    public static final String game = "sliding puzzle";
    private static ScoreService scoreService = new ScoreServiceJDBC();
    private static CommentService commentService = new CommentServiceJDBC();
    private static RatingService ratingService = new RatingServiceJDBC();
    private static boolean running;
    private static Date date = new Date();
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        running = true;
        while(running){
            System.out.println("Welcome to Sliding puzzle.\nPick your choice:");
            System.out.println(" 1. Play\n 2. Leaderboard\n 3. Comments\n 4. Write a comment\n 5. Rate the game\n 6. Exit");
            System.out.println("Your choice: ");
            var choice = scanner.nextLine();
            switch (choice){
                case "1":
                    System.out.println("What size would you want to play. Must be at least 3x3 big.\nEnter rows: ");
                    int rows = scanner.nextInt();
                    if(rows<3){
                        System.out.println("Too small. Must be at least 3");
                        break;
                    }
                    System.out.println("Enter columns: ");
                    int columns = scanner.nextInt();
                    if(columns<3){
                        System.out.println("Too small. Must be at least 3");
                        break;
                    }
                    Field field = new Field(rows,columns);
                    ConsoleUI ui = new ConsoleUI(field);
                    ui.play();
                    break;
                case "2":
                    var scores = scoreService.getTopScores(game);
                    for(int i=0; i<scores.size() ;i++){
                        System.out.println(scoreService.getTopScores(game).get(i).getPlayer()
                                + "- "
                                + scoreService.getTopScores(game).get(i).getPoints()
                                + " "
                                + scoreService.getTopScores(game).get(i).getPlayedOn()
                        );
                    }
                    System.out.println("\n");
                    break;
                case  "3":
                    var comments = commentService.getComments(game);
                    for(int i=0; i<comments.size() ;i++){
                        System.out.println(comments.get(i).getPlayer()
                                + ": "
                                + comments.get(i).getComment()
                                + " "
                                + comments.get(i).getCommentedOn()
                        );
                    }
                    System.out.println("\n");
                    break;
                case "4":
                    System.out.print("Enter name ");
                    var name = scanner.nextLine();
                    System.out.print("Your comment: ");
                    var comment = scanner.nextLine();
                    commentService.addComment(new Comment(name,game,comment,date));
                    break;
                case "5":
                    System.out.println("Average rating of this game is: " +ratingService.getAverageRating(game));
                    System.out.println("Enter name: ");
                    name = scanner.nextLine();
                    System.out.println("Rate from 0-5: ");
                    var rating = scanner.nextInt();
                    ratingService.setRating(new Rating(name,game,rating));
                    break;
                case "6":
                    running = false;
                    break;
            }
        }
    }


}
