package sk.tuke.gamestudio.ui;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.game.Field;
import sk.tuke.gamestudio.entity.Score;
import sk.tuke.gamestudio.services.CommentService;
import sk.tuke.gamestudio.services.RatingService;
import sk.tuke.gamestudio.services.ScoreService;

import java.util.Date;
import java.util.Scanner;


public class ConsoleUI {

    private Scanner scanner = new Scanner(System.in);
    private int digits;
    private boolean running;
    public static final String game = "sliding_puzzle";

    @Autowired
    private CommentService commentService;
    @Autowired
    private RatingService ratingService;
    @Autowired
    private ScoreService scoreService ;
    private Field field;

    private static Date date = new Date();



    public ConsoleUI(Field field) {
        this.field = field;
    }


    public void play() {
        running = true;
        digits = String.valueOf(field.getColumnCount() * field.getRowCount()).length();
        int score = field.getColumnCount()* field.getRowCount()*100;
        while (running) {
            if(field.isSolved()){
                running = false;
                break;
            }
            printField();
            System.out.print("Enter command (W - UP, A - LEFT, S - DOWN, D - RIGHT, SHUFFLE - reset), E - exit: ");
            var line = scanner.nextLine().toUpperCase();
            switch (line) {
                case "W":
                    if (field.getEmptyRow() - 1 >= 0) {
                        field.SwapTiles(field.getEmptyRow(), field.getEmptyCol(), field.getEmptyRow() - 1, field.getEmptyCol());
                        field.setEmptyRow(field.getEmptyRow() - 1);
                    } else {
                        System.out.println("Invalid input");
                    }
                    // code block
                    break;
                case "D":
                    if (field.getEmptyCol() + 1 < field.getColumnCount()) {
                        field.SwapTiles(field.getEmptyRow(), field.getEmptyCol(), field.getEmptyRow(), field.getEmptyCol() + 1);
                        field.setEmptyCol(field.getEmptyCol() + 1);
                    } else {
                        System.out.println("Invalid input");
                    }
                    // code block
                    break;
                case "S":
                    if (field.getEmptyRow() + 1 < field.getRowCount()) {
                        field.SwapTiles(field.getEmptyRow(), field.getEmptyCol(), field.getEmptyRow() + 1, field.getEmptyCol());
                        field.setEmptyRow(field.getEmptyRow() + 1);
                    } else {
                        System.out.println("Invalid input");
                    }
                    break;
                case "A":
                    if (field.getEmptyCol() - 1 >= 0) {
                        field.SwapTiles(field.getEmptyRow(), field.getEmptyCol(), field.getEmptyRow(), field.getEmptyCol() - 1);
                        field.setEmptyCol(field.getEmptyCol() - 1);
                    } else {
                        System.out.println("Invalid input");
                    }
                    break;
                case "SHUFFLE":
                    score = field.getColumnCount()* field.getRowCount()*100;
                    field.shuffle();
                    break;
                case "E":
                    running = false;
                    break;
                default:
                    // code block
                    System.out.println("Please w,a,s,d");
            }
            System.out.println("steps: " + field.getSteps());
            if(score!=0){
                score = score-1;
            }

            System.out.println("score: " + score);
        }
        if(field.isSolved()){
            printField();
            System.out.println("You won congratulations");
            System.out.println("Your score: "+ score);
            System.out.print("Import name ");
            var name = scanner.nextLine();
            scoreService.addScore(new Score( name,"sliding_puzzle",score,date));
        }
    }

    private void printField() {
        System.out.println("Game state: " + field.getState());
        for (int row = 0; row < field.getRowCount(); row++) {
            if (row == 0) {
                printheader();
                System.out.print("\n");
            }
            for (int column = 0; column < field.getColumnCount(); column++) {

                System.out.print("\u2551 ");
                for (int length = String.valueOf(field.tiles[row][column]).length(); length < digits; length++) {
                    if (String.valueOf(field.tiles[row][column]).length() < digits) {
                        if (field.tiles[row][column] == 0) {
                            System.out.print("\u001B[31m0\u001B[0m");
                        } else {
                            System.out.print("0");
                        }
                    }
                }
                if (field.tiles[row][column] == 0) {
                    System.out.print("\u001B[31m" + field.tiles[row][column] + "\u001B[0m ");
                    if (column == field.getColumnCount() - 1) {
                        System.out.print("\u2551");
                    }
                } else {
                    System.out.print(field.tiles[row][column] + " ");
                    if (column == field.getColumnCount() - 1) {
                        System.out.print("\u2551");
                    }
                }

            }
            System.out.println(" ");
            if (row == field.getRowCount() - 1) {
                printfooter();
                System.out.print("\n");
            } else {
                printmid();
                System.out.print("\n");
            }

        }

    }

    private void printmid() {
        System.out.print("\u2560");
        for (int column = 0; column < field.getColumnCount() - 1; column++) {
            for (int length = 1; length < digits; length++) {
                System.out.print("\u2550");
            }
            System.out.print("\u2550\u2550\u2550\u256C");
        }
        for (int length = 1; length < digits; length++) {
            System.out.print("\u2550");
        }
        System.out.print("\u2550\u2550\u2550\u2563");
    }

    private void printfooter() {
        System.out.print("\u255A");
        for (int column = 0; column < field.getColumnCount() - 1; column++) {
            for (int length = 1; length < digits; length++) {
                System.out.print("\u2550");
            }
            System.out.print("\u2550\u2550\u2550\u2569");
        }
        for (int length = 1; length < digits; length++) {
            System.out.print("\u2550");
        }
        System.out.print("\u2550\u2550\u2550\u255D");
    }

    private void printheader() {
        System.out.print("\u2554");
        for (int column = 0; column < field.getColumnCount() - 1; column++) {
            for (int length = 1; length < digits; length++) {
                System.out.print("\u2550");
            }
            System.out.print("\u2550\u2550\u2550\u2566");
        }
        for (int length = 1; length < digits; length++) {
            System.out.print("\u2550");
        }
        System.out.print("\u2550\u2550\u2550\u2557");
    }

    public void menu(){
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
                    field.setColumnCount(columns);
                    field.setRowCount(rows);
                    play();
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
