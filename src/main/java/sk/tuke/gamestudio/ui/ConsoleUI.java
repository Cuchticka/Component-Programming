package sk.tuke.gamestudio.ui;

import sk.tuke.gamestudio.entity.Field;

import javax.sound.midi.Soundbank;
import java.util.Scanner;

public class ConsoleUI {
    private final Field field;
    private Scanner scanner = new Scanner(System.in);
    private int digits;

    public ConsoleUI(Field field) {
        this.field = field;
    }

    public void play() {
        digits = String.valueOf(field.getColumnCount() * field.getRowCount()).length();
        while (!field.isSolved()) {
            printField();
            System.out.print("Enter command (W - UP, A - LEFT, S - DOWN, D - RIGHT, SHUFFLE - reset): ");
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
                    field.shuffle();
                    break;
                default:
                    // code block
                    System.out.println("Please w,a,s,d");
            }
            System.out.println("steps: " + field.getSteps());
        }
        printField();
        System.out.println("VYHRAL SI CONGRATS");
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
}
