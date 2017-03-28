package com.example.Xsi0;

/**
 * Created by adrians on 24.03.2017.
 */
public class Cell {
    int row, col;
    CellContent content;


    public Cell(int row, int col, CellContent content) {
        this.row = row;
        this.col = col;
        this.content = content;
    }



    public void paintCell() {
        switch (content) {
            case EMPTY:
                System.out.print("   ");
                break;
            case O:
                System.out.print(" O ");
                break;
            case X:
                System.out.print(" X ");
                break;
        }

    }
}
