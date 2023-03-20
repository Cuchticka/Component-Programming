package sk.tuke.gamestudio;



import sk.tuke.gamestudio.entity.Field;
import sk.tuke.gamestudio.ui.ConsoleUI;


public class Puzzle {

    public static void main(String[] args) {
        var field = new Field(3, 4);
        var ui = new ConsoleUI(field);
        ui.play();
    }

}
