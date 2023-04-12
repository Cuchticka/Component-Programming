package sk.tuke.gamestudio.game;



import org.springframework.stereotype.Component;

import java.util.Random;


public class Field {

    private int rowCount;
    private int columnCount;
    private int emptyCol;
    private int emptyRow;

    private int steps;

    private boolean win;

    private FieldState state = FieldState.PLAYING;
    public int[][] tiles;


    public Field(int rowCount, int columnCount) {
        win = true;
        this.rowCount = rowCount;
        this.columnCount = columnCount;
        tiles = new int[rowCount][columnCount];
        shuffle();

    }


    public void shuffle() {
        int num = 1;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                tiles[row][col] = num;
                num++;
            }
        }
        emptyCol = columnCount - 1;
        emptyRow = rowCount - 1;
        tiles[emptyRow][emptyCol] = 0;

        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {

            int randrow = rand.nextInt(rowCount);
            int randcol = rand.nextInt(columnCount);
            int randrow1 = rand.nextInt(rowCount);
            int randcol1 = rand.nextInt(columnCount);

            if (tiles[randrow1][randcol1] != 0 && tiles[randrow][randcol] != 0) {
                if(!win){
                    SwapTiles(randrow, randcol, randrow1, randcol1);
                }

            }
            steps = 0;

        }

        if(win){
            SwapTiles(rowCount-1,columnCount-1,rowCount-2,rowCount-1);
            setEmptyRow(rowCount-2);
        }



    }

    public void SwapTiles(int row1, int col1, int row2, int col2) {
        int temp = tiles[row1][col1];
        tiles[row1][col1] = tiles[row2][col2];
        tiles[row2][col2] = temp;
        steps++;
    }

    public boolean isSolved() {
        int num = 1;
        for (int row = 0; row < rowCount; row++) {
            for (int col = 0; col < columnCount; col++) {
                if (row == rowCount - 1 && col == columnCount - 1) {
                    num = 0;
                }
                if (tiles[row][col] != num) {
                    return false;
                }
                num++;
            }
        }
        state = FieldState.SOLVED;
        //System.out.println("VYHRAL SI CONGRATS");
        return true;
    }


    public int getSteps() {
        return steps;
    }

    public FieldState getState() {
        return state;
    }

    public int getColumnCount() {
        return columnCount;
    }

    public void setColumnCount(int columnCount) {
        this.columnCount = columnCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

    public int getRowCount() {
        return rowCount;
    }

    public int getEmptyCol() {
        return emptyCol;
    }

    public void setEmptyCol(int emptyCol) {
        this.emptyCol = emptyCol;
    }

    public int getEmptyRow() {
        return emptyRow;
    }

    public void setEmptyRow(int emptyRow) {
        this.emptyRow = emptyRow;
    }
}
