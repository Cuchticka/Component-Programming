package sk.tuke.gamestudio.game;

import org.springframework.beans.factory.annotation.Autowired;
import sk.tuke.gamestudio.entity.Comment;
import sk.tuke.gamestudio.game.Field;
import sk.tuke.gamestudio.entity.Rating;
import sk.tuke.gamestudio.services.*;
import sk.tuke.gamestudio.ui.ConsoleUI;

import java.util.Date;
import java.util.Scanner;


public class Puzzle {

    @Autowired
    private static ConsoleUI ui;

    public static void main(String[] args) {
        ui.menu();
    }



}
