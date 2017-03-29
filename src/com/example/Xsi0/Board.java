package com.example.Xsi0;

import java.util.ArrayList;

/**
 * Created by adrians on 17.03.2017.
 */

public class Board {

    public int currentRow;
    public int currentCol;
    private int ROWS = 3;
    private int COLS = 3;
    public ArrayList<String> availablePositions = new ArrayList<>(9);

    Cell[][] cells;

    public Board() {
        cells = new Cell[ROWS][COLS]; //create array of Cells
        for(currentRow = 0; currentRow < ROWS; currentRow++) {
            for(currentCol = 0; currentCol < COLS; currentCol++) {
                cells[currentRow][currentCol] = new Cell(currentRow, currentCol, CellContent.EMPTY); //create cell objects
                availablePositions.add(Integer.toString(currentRow+1)+Integer.toString(currentCol+1));
            }
        }

    }

    public void reInit() {
        availablePositions.clear();
        for(currentRow = 0; currentRow < ROWS; currentRow++) {
            for(currentCol = 0; currentCol < COLS; currentCol++) {
                cells[currentRow][currentCol].content = CellContent.EMPTY; //create cell objects
                availablePositions.add(Integer.toString(currentRow+1)+Integer.toString(currentCol+1));
            }
        }
    }

    public void setCells(int currentRow, int currentCol, CellContent symbol) {
        this.currentRow = currentRow-1;
        this.currentCol = currentCol-1;
        cells[this.currentRow][this.currentCol].content = symbol;
        availablePositions.remove(Integer.toString(currentRow)+currentCol);
        System.out.println(availablePositions);
    }

    public boolean wins(AbstractPlayer player) {
       return //row check

               (cells[currentRow][0].content == player.playerSymbol &&
                       cells[currentRow][1].content == player.playerSymbol &&
                       cells[currentRow][2].content == player.playerSymbol ||
                       //col check
                       cells[0][currentCol].content == player.playerSymbol &&
                               cells[1][currentCol].content == player.playerSymbol &&
                               cells[2][currentCol].content == player.playerSymbol ||
                       //main diagonal
                cells[0][0].content == player.playerSymbol &&
                        cells[1][1].content == player.playerSymbol &&
                        cells[2][2].content == player.playerSymbol ||
                       //other diagonal
                       cells[0][2].content == player.playerSymbol &&
                               cells[1][1].content == player.playerSymbol &&
                               cells[2][0].content == player.playerSymbol);
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


    public boolean positionValidator(String inputPosition) {
        String pattern = "^[1-3]{2}$";
        boolean isValid = inputPosition.matches(pattern);
//        boolean isCellEmpty = true;
        if(!isValid) {
            System.out.println("Invalid please retry: ");
            return false;
        }
        else {
            getIntPositions(inputPosition);
            boolean isCellEmpty = isCellFree(currentRow-1, currentCol-1);
            if(!isCellEmpty) {
                System.out.println("Cell not empty: ");
                return false;
            }
        }
        return true;
    }

    public void getIntPositions(String inputPosition) {
        this.currentRow = Character.getNumericValue(inputPosition.charAt(0));
        this.currentCol = Character.getNumericValue(inputPosition.charAt(1));
    }




}
