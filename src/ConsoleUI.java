import java.util.Scanner;

public class ConsoleUI {
    private final Field field;
    private Scanner scanner = new Scanner(System.in);
    public ConsoleUI(Field field){
        this.field = field;
    }

    public void play(){
        while(!field.isSolved()){
            printField();
            System.out.print("Enter command (W - UP, A - LEFT, S - DOWN, D - RIGHT, SHUFFLE - reset): ");
            var line = scanner.nextLine().toUpperCase();
            switch(line) {
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
            System.out.println("steps: "+field.getSteps());
        }
        printField();
        System.out.println("VYHRAL SI CONGRATS");
    }

    private void printField(){
        System.out.println("Game state: " + field.getState());
        for(int row =0; row<field.getRowCount();row++){
            for(int column = 0; column<field.getColumnCount();column++){
                if(field.tiles[row][column] < 10 && field.tiles[row][column]!=0 && field.getColumnCount()>9 && field.getRowCount()>9){
                    System.out.print("0");
                }
                System.out.print(field.tiles[row][column] + " ");
            }
            System.out.println("\n");
        }

    }
}
