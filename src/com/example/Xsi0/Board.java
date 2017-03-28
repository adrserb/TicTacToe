package com.example.Xsi0;

/**
 * Created by adrians on 17.03.2017.
 */

public class Board {

    public int currentRow;
    public int currentCol;
    private int ROWS = 3;
    private int COLS = 3;

    Cell[][] cells;

    public Board() {
        cells = new Cell[ROWS][COLS]; //create array of Cells
        for(currentRow = 0; currentRow < ROWS; currentRow++) {
            for(currentCol = 0; currentCol < COLS; currentCol++) {
                cells[currentRow][currentCol] = new Cell(currentRow, currentCol, CellContent.EMPTY); //create cell objects
            }
        }

    }

    public void reInit() {
        for(currentRow = 0; currentRow < ROWS; currentRow++) {
            for(currentCol = 0; currentCol < COLS; currentCol++) {
                cells[currentRow][currentCol].content = CellContent.EMPTY; //create cell objects
            }
        }
    }

    public void setCells(int currentRow, int currentCol, CellContent symbol) {
        this.currentRow = currentRow-1;
        this.currentCol = currentCol-1;
        cells[this.currentRow][this.currentCol].content = symbol;

    }

    public boolean wins(CellContent symbol) {
       return //row check

               (cells[currentRow][0].content == symbol &&
                       cells[currentRow][1].content == symbol &&
                       cells[currentRow][2].content == symbol ||
                       //col check
                       cells[0][currentCol].content == symbol &&
                               cells[1][currentCol].content == symbol &&
                               cells[2][currentCol].content == symbol ||
                       //main diagonal
                cells[0][0].content == symbol &&
                        cells[1][1].content == symbol &&
                        cells[2][2].content == symbol ||
                       //other diagonal
                       cells[0][2].content == symbol &&
                               cells[1][1].content == symbol &&
                               cells[2][0].content == symbol);
    }

    public boolean isTie() {
        for(int i = 0; i<ROWS; i++) {
            for(int j = 0; j<COLS; j++) {
                if(cells[i][j].content == CellContent.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }



    public void paint() {
        for(currentRow = 0; currentRow < ROWS; currentRow++) {
            for(currentCol = 0; currentCol < COLS; currentCol++) {
                cells[currentRow][currentCol].paintCell();
                if(currentCol < 2) {
                    System.out.print("|");
                }
            }
            if(currentRow < 2) {
                System.out.println();
                System.out.println("-----------");
            }
        }
        System.out.println();
    }
    public boolean isCellFree(int selectedRow, int selectedCol) {
        return cells[selectedRow][selectedCol].content == CellContent.EMPTY;
    }


}
